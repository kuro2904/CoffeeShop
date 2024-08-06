package vn.com.ltdt.Coffee_Shop.order.dtos;

import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDTO;
import vn.com.ltdt.Coffee_Shop.product.dtos.ProductDetailDTO;

public record OrderDetailDTO(
        ProductDetailDTO productDetail,
        ProductDTO product,
        int quantity,
        double price
) {
}
