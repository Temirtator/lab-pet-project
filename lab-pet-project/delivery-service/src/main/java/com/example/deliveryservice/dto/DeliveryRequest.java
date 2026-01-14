package com.example.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Delivery creation request")
public class DeliveryRequest {

    @NotNull
    @Schema(example = "1 or 2 etc.")
    private Long productId;

    @NotBlank
    @Schema(example = "Almaty, Abay 10")
    private String address;

    public DeliveryRequest() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
