package kz.lab.petproject.client;

import kz.lab.petproject.client.dto.DeliveryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DeliveryClient {

    private final WebClient webClient;

    public DeliveryClient(@Value("${delivery.service.url:http://delivery-service:8081}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<Void> createDelivery(DeliveryRequest request) {
        return webClient.post()
                .uri("/delivery")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> Mono.empty());
    }
}
