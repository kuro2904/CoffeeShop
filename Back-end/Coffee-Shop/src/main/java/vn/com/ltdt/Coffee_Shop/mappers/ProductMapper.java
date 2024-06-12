package vn.com.ltdt.Coffee_Shop.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;

@Service
public class ProductMapper {


    public ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory().getId(),
                product.isActive(),
                product.getProductDetails().stream().map(this::mapToDetailDTO).toList()
        );
    }

    private ProductDetailDTO mapToDetailDTO(ProductDetail detail) {
        return new ProductDetailDTO(
                detail.getId(),
                detail.getProduct().getId(),
                detail.getSize(),
                detail.getPrice()
        );
    }

}
