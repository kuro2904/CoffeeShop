package vn.com.ltdt.Coffee_Shop.utils.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.images.Image;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;
import vn.com.ltdt.Coffee_Shop.rating.Rating;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;
import vn.com.ltdt.Coffee_Shop.rating.RatingDTO;

@Service
@RequiredArgsConstructor
public class ProductMapper {


    public ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRatings().isEmpty() ? 0.0 : product.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0.0),
                product.getRatings().size(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.isActive(),
                product.getProductDetails().stream().map(this::mapToDetailDTO).toList(),
                product.getImages().stream().map(Image::getUrl).toList()
        );
    }

    public ProductDetailDTO mapToDetailDTO(ProductDetail detail) {
        return new ProductDetailDTO(
                detail.getId(),
                detail.getProduct().getId(),
                detail.getSize(),
                detail.getPrice()
        );
    }

}
