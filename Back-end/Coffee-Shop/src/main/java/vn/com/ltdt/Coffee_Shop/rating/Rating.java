package vn.com.ltdt.Coffee_Shop.rating;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.com.ltdt.Coffee_Shop.product.Product;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_rating")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double rating;
    @ManyToOne
    private Product product;
}
