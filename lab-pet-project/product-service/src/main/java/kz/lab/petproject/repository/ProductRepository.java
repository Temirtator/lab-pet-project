package kz.lab.petproject.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import kz.lab.petproject.entity.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
