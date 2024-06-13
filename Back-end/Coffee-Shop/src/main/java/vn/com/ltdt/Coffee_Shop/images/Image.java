package vn.com.ltdt.Coffee_Shop.images;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.ltdt.Coffee_Shop.product.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
