package vn.com.ltdt.Coffee_Shop.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.ltdt.Coffee_Shop.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
