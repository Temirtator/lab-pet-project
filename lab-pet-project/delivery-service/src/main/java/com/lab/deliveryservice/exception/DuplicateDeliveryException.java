package com.lab.deliveryservice.exception;

public class DuplicateDeliveryException extends RuntimeException {

    private final Long retryAfterMs;

    public DuplicateDeliveryException(String message, Long retryAfterMs) {
        super(message);
        this.retryAfterMs = retryAfterMs;
    }

    public Long getRetryAfterMs() {
        return retryAfterMs;
    }
}
