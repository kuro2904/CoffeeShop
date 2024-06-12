package vn.com.ltdt.Coffee_Shop.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Size;
    @NotNull(message = "The price cannot be null")
    private double price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
