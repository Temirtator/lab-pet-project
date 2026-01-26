package kz.lab.reportservice.client;

import kz.lab.reportservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient client;

    public ProductClient(@Value("${product.service.url:http://product-service:8080}") String baseUrl) {
        this.client = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<ProductDto> getProduct(Long id) {
        return client.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .onErrorResume(e -> Mono.empty());
    }
}
