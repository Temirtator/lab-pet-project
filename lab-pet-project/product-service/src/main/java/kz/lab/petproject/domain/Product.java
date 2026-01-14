package kz.lab.petproject.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Schema(description = "Product creation request")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "iPhone")
    private String name;

    @Schema(example = "350000")
    private BigDecimal price;

    @Schema(example = "Almaty, Abay 10")
    private String address;

    public Product() {}

    public Product(String name, BigDecimal price, String address) {
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setAddress(String address) { this.address = address; }
}
