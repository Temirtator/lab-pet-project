package kz.lab.petproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.lab.petproject.domain.Product;
import kz.lab.petproject.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping
    public CompletableFuture<List<Product>> getAll() {
        return service.getAllAsync();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(
        @PathVariable Long id,
        @RequestBody Product product
    ) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/whoami")
    public String whoami(Authentication authentication) {
        return authentication.getName() + " " + authentication.getAuthorities();
    }

}
