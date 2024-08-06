package vn.com.ltdt.Coffee_Shop.rating;

import jakarta.persistence.*;
import lombok.Data;
import vn.com.ltdt.Coffee_Shop.product.Product;

@Entity
@Table(name = "t_rating")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double rating = 0;
    @ManyToOne
    private Product product;
}
