package vn.com.ltdt.Coffee_Shop.order.dtos;

public record OrderDetailDTO(
        int productDetailId,
        int productId,
        int quantity,
        double price
) {
}
