package com.lab.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.deliveryservice.dto.DeliveryRequest;
import com.lab.deliveryservice.dto.DeliveryCreateResponse;
import com.lab.deliveryservice.services.DeliveryService;
import com.lab.deliveryservice.dto.DeliveryStatusResponse;
import com.lab.deliveryservice.exception.DuplicateDeliveryException;

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
    public ResponseEntity<DeliveryCreateResponse> createDelivery(
            @RequestBody @Valid DeliveryRequest request
    ) {
        DeliveryCreateResponse response = deliveryService.processDelivery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateDeliveryException.class)
    public ResponseEntity<DeliveryCreateResponse> handleDuplicate(DuplicateDeliveryException ex) {
        DeliveryCreateResponse response = DeliveryCreateResponse.duplicate(ex.getMessage(), ex.getRetryAfterMs());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/{id}")
    public DeliveryStatusResponse getStatus(@PathVariable Long id) {
        return deliveryService.getStatus(id);
    }
}
