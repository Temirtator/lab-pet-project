package com.lab.deliveryservice.services;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lab.deliveryservice.dto.DeliveryRequest;
import com.lab.deliveryservice.dto.DeliveryCreateResponse;
import com.lab.deliveryservice.dto.DeliveryStatusResponse;
import com.lab.deliveryservice.entity.Delivery;
import com.lab.deliveryservice.enums.DeliveryStatus;
import com.lab.deliveryservice.exception.DuplicateDeliveryException;
import com.lab.deliveryservice.repository.DeliveryRepository;

@Service
public class DeliveryService {

    private static final Duration DUPLICATE_WINDOW = Duration.ofSeconds(2);

    private final DeliveryRepository repository;
    private final StringRedisTemplate redisTemplate;

    public DeliveryService(DeliveryRepository repository, StringRedisTemplate redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    public DeliveryCreateResponse processDelivery(DeliveryRequest request) {
        preventDuplicate(request);

        Delivery delivery = new Delivery();
        delivery.setProductId(request.getProductId());
        delivery.setAddress(request.getAddress());
        delivery.setStatus(DeliveryStatus.CREATED);

        Delivery saved = repository.save(delivery);
        return DeliveryCreateResponse.success(saved.getId());
    }

    public DeliveryStatusResponse getStatus(Long productId) {
        return repository.findByProductId(productId)
                .map(d -> new DeliveryStatusResponse(
                        d.getProductId(),
                        d.getStatus().name()
                ))
                .orElseThrow();
    }

    private void preventDuplicate(DeliveryRequest request) {
        String key = buildDuplicateKey(request);
        Boolean stored = redisTemplate.opsForValue()
                .setIfAbsent(key, "1", DUPLICATE_WINDOW);

        if (Boolean.FALSE.equals(stored)) {
            throw new DuplicateDeliveryException("Duplicate delivery request", DUPLICATE_WINDOW.toMillis());
        }
    }

    private String buildDuplicateKey(DeliveryRequest request) {
        String raw = request.getProductId() + "|" + request.getAddress();
        return "delivery:dedupe:" + sha256(raw);
    }

    private String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 not available", ex);
        }
    }
}
