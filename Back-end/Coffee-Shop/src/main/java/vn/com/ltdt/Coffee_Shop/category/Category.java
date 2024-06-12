package vn.com.ltdt.Coffee_Shop.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.ltdt.Coffee_Shop.product.Product;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "The name cannot be null")
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "The type cannot be null")
    private Type type;
}
