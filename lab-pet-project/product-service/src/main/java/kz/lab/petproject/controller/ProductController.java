package kz.lab.petproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.lab.petproject.entity.Product;
import kz.lab.petproject.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Product management API")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create product and start delivery")
    public Mono<Product> create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping
    public Flux<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(
        @PathVariable Long id,
        @RequestBody Product product
    ) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/whoami")
    public String whoami(Authentication authentication) {
        return authentication.getName() + " " + authentication.getAuthorities();
    }

}
