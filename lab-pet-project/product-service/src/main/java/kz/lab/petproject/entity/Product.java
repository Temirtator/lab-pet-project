package kz.lab.petproject.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table("product")
@Schema(description = "Product creation request")
public class Product {

    @Id
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
