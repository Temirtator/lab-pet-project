package com.lab.deliveryservice.repository;

import java.util.Optional;

import com.lab.deliveryservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByProductId(Long productId);
}
