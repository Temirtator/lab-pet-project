package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.DeliveryRequest;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @PostMapping
    public void create(@RequestBody DeliveryRequest request) {
        System.out.println("Deliver product " + request.getProductId() + " to " + request.getAddress());
    }
}

