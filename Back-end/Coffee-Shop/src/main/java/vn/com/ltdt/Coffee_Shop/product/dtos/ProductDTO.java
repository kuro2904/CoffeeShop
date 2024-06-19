package vn.com.ltdt.Coffee_Shop.product.dtos;

import jakarta.validation.constraints.NotNull;
import vn.com.ltdt.Coffee_Shop.images.ImageDTO;

import java.util.List;

public record ProductDTO(
       int id,
       @NotNull(message = "The name cannot be null")
       String name,
       @NotNull(message = "The description cannot be null")
       String description,
       @NotNull(message = "The category Id cannot be null")
       int categoryId,
       String categoryName,
       boolean isActive,
       @NotNull(message = "The details cannot be null")
       List<ProductDetailDTO> details,
       List<String> images
) {
}
