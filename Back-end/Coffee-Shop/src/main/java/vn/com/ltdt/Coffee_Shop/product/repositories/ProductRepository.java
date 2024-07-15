package vn.com.ltdt.Coffee_Shop.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.ltdt.Coffee_Shop.category.Category;
import vn.com.ltdt.Coffee_Shop.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);
}
