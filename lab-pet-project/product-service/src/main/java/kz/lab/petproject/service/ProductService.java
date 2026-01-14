package kz.lab.petproject.service;

import kz.lab.petproject.client.DeliveryClient;
import kz.lab.petproject.client.dto.DeliveryRequest;
import kz.lab.petproject.domain.Product;
import kz.lab.petproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final DeliveryClient deliveryClient;

    public ProductService(ProductRepository repository, DeliveryClient deliveryClient) {
        this.repository = repository;
        this.deliveryClient = deliveryClient;
    }

    public Product create(Product product) {
        Product saved = repository.save(product);

        deliveryClient.createDelivery(
            new DeliveryRequest(saved.getId(), saved.getAddress())
        );

        return saved;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product update(Long id, Product updated) {
        Product product = findById(id);
        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setAddress(updated.getAddress());
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
