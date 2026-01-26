package kz.lab.reportservice.client;

import kz.lab.reportservice.dto.DeliveryStatusDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DeliveryClient {

    private final WebClient client;

    public DeliveryClient(@Value("${delivery.service.url:http://delivery-service:8081}") String baseUrl) {
        this.client = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<DeliveryStatusDto> getStatus(Long productId) {
        return client.get()
                .uri("/delivery/{id}", productId)
                .exchangeToMono(resp -> {
                    if (resp.statusCode().equals(HttpStatus.OK)) {
                        return resp.bodyToMono(DeliveryStatusDto.class);
                    }
                    if (resp.statusCode().is4xxClientError()) {
                        return Mono.empty();
                    }
                    return Mono.empty();
                })
                .onErrorResume(e -> Mono.empty());
    }
}
