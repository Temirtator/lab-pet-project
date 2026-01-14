package com.example.deliveryservice.controller;

import com.example.deliveryservice.Services.DeliveryService;
import com.example.deliveryservice.dto.DeliveryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
@Tag(name = "Delivery", description = "Delivery management API")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @Operation(summary = "Create delivery")
    public ResponseEntity<Void> createDelivery(
            @RequestBody @Valid DeliveryRequest request
    ) {
        deliveryService.processDelivery(request);
        return ResponseEntity.ok().build();
    }
}

