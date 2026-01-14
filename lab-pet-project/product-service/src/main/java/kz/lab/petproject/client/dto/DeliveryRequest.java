package kz.lab.petproject.client.dto;

public class DeliveryRequest {
    private Long productId;
    private String address;

    public DeliveryRequest(Long productId, String address) {
        this.productId = productId;
        this.address = address;
    }

    public Long getProductId() {
        return productId;
    }

    public String getAddress() {
        return address;
    }
}
