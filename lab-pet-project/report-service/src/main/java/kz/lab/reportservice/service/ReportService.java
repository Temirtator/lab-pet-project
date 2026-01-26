package kz.lab.reportservice.service;

import kz.lab.reportservice.client.DeliveryClient;
import kz.lab.reportservice.client.ProductClient;
import kz.lab.reportservice.dto.ReportItem;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReportService {

    private final ProductClient productClient;
    private final DeliveryClient deliveryClient;

    public ReportService(ProductClient productClient, DeliveryClient deliveryClient) {
        this.productClient = productClient;
        this.deliveryClient = deliveryClient;
    }

    public Mono<List<ReportItem>> buildReport(List<Long> ids) {
        return Flux.fromIterable(ids)
                .flatMap(id -> productClient.getProduct(id)
                        .flatMap(product -> deliveryClient.getStatus(id)
                                .map(ds -> new ReportItem(id, product.name(), ds.status()))
                                .defaultIfEmpty(new ReportItem(id, product.name(), "UNKNOWN"))
                        )
                        .onErrorResume(e -> Mono.empty())
                )
                .collectList();
    }
}
