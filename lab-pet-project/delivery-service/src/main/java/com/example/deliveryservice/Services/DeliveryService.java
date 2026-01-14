package com.example.deliveryservice.Services;

import com.example.deliveryservice.dto.DeliveryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private static final Logger log =
            LoggerFactory.getLogger(DeliveryService.class);

    public void processDelivery(DeliveryRequest request) {
        log.info(
                "Delivery created for productId={} to address={}",
                request.getProductId(),
                request.getAddress()
        );
    }
}
