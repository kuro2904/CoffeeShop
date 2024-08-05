package vn.com.ltdt.Coffee_Shop.order.dtos;

public record OrderDetailDTO(
        int productDetail,
        int product,
        int quantity,
        double price
) {
}
