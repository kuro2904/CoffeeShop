package vn.com.ltdt.Coffee_Shop.order.dtos;

public record OrderDetailDTO(
        int productId,
        int quantity,
        double price
) {
}
