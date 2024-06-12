package vn.com.ltdt.Coffee_Shop.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
}
