package vn.com.ltdt.Coffee_Shop.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.ltdt.Coffee_Shop.category.Category;
import vn.com.ltdt.Coffee_Shop.images.Image;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "The name cannot be null")
    private String name;

    @NotNull(message = "The description cannot be null")
    private String description;
    @NotNull(message = "The category cannot be null")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull(message = "The details cannot be null")
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductDetail> productDetails = new ArrayList<>();
    private boolean isActive = true;
    @OneToMany(mappedBy = "product")
    private List<Image> images;
}
