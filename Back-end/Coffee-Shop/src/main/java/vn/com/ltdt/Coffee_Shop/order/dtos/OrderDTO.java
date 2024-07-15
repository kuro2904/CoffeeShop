package vn.com.ltdt.Coffee_Shop.order.dtos;

import java.util.List;

public record OrderDTO(
    String paymentMethod,
    double amount,
    String note,
    String address,
    String customerId,
    String employeeId,
    String status,
    List<OrderDetailDTO> details
) {
}
