package com.lab.deliveryservice.services;

import org.springframework.stereotype.Service;

import com.lab.deliveryservice.dto.DeliveryRequest;
import com.lab.deliveryservice.dto.DeliveryStatusResponse;
import com.lab.deliveryservice.entity.Delivery;
import com.lab.deliveryservice.enums.DeliveryStatus;
import com.lab.deliveryservice.repository.DeliveryRepository;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public void processDelivery(DeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setProductId(request.getProductId());
        delivery.setAddress(request.getAddress());
        delivery.setStatus(DeliveryStatus.CREATED);

        repository.save(delivery);
    }

    public DeliveryStatusResponse getStatus(Long productId) {
        return repository.findByProductId(productId)
                .map(d -> new DeliveryStatusResponse(
                        d.getProductId(),
                        d.getStatus().name()
                ))
                .orElseThrow();
    }
}
