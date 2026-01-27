package com.lab.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Delivery creation response")
public class DeliveryCreateResponse {

    @Schema(description = "Whether request was detected as duplicate")
    private boolean duplicate;

    @Schema(description = "Created delivery id when not duplicate")
    private Long deliveryId;

    @Schema(description = "Human-readable message")
    private String message;

    @Schema(description = "How many milliseconds to wait before retrying")
    private Long retryAfterMs;

    public DeliveryCreateResponse() {
    }

    public DeliveryCreateResponse(boolean duplicate, Long deliveryId, String message, Long retryAfterMs) {
        this.duplicate = duplicate;
        this.deliveryId = deliveryId;
        this.message = message;
        this.retryAfterMs = retryAfterMs;
    }

    public static DeliveryCreateResponse success(Long deliveryId) {
        return new DeliveryCreateResponse(false, deliveryId, "Delivery created", null);
    }

    public static DeliveryCreateResponse duplicate(String message, Long retryAfterMs) {
        return new DeliveryCreateResponse(true, null, message, retryAfterMs);
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRetryAfterMs() {
        return retryAfterMs;
    }

    public void setRetryAfterMs(Long retryAfterMs) {
        this.retryAfterMs = retryAfterMs;
    }
}
