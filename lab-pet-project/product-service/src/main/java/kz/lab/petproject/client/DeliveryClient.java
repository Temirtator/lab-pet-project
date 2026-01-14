package kz.lab.petproject.client;

import kz.lab.petproject.client.dto.DeliveryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service", url = "http://delivery-service:8081")
public interface DeliveryClient {

    @PostMapping("/delivery")
    void createDelivery(@RequestBody DeliveryRequest request);
}
