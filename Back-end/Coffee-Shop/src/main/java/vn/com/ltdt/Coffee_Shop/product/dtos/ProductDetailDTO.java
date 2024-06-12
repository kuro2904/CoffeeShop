package vn.com.ltdt.Coffee_Shop.product.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductDetailDTO(
        int id,
        @NotNull(message = "Product ID cannot be null")
        int productId,
        String size,
        @NotNull(message = "Product Price cannot be null")
        double price
) {

}
