package kz.lab.petproject.service;

import kz.lab.petproject.client.DeliveryClient;
import kz.lab.petproject.client.dto.DeliveryRequest;
import kz.lab.petproject.entity.Product;
import kz.lab.petproject.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final DeliveryClient deliveryClient;

    public ProductService(ProductRepository repository, DeliveryClient deliveryClient) {
        this.repository = repository;
        this.deliveryClient = deliveryClient;
    }

    public Mono<Product> create(Product product) {
        return repository.save(product)
                .flatMap(saved -> deliveryClient.createDelivery(
                        new DeliveryRequest(saved.getId(), saved.getAddress())
                ).thenReturn(saved));
    }

    public Flux<Product> getAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    public Mono<Product> update(Long id, Product updated) {
        return findById(id)
                .flatMap(product -> {
                    product.setName(updated.getName());
                    product.setPrice(updated.getPrice());
                    product.setAddress(updated.getAddress());
                    return repository.save(product);
                });
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}
