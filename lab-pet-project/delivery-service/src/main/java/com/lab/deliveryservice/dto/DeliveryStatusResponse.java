package com.lab.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Delivery status response")
public class DeliveryStatusResponse {
  
    @NotNull
    @Schema(example = "1 or 2 etc.")
    private Long productId;

    @NotBlank
    @Schema(example = "Almaty, Abay 10")
    private String status;

    public DeliveryStatusResponse(Long productId, String status) {
        this.productId = productId;
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}