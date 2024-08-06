package vn.com.ltdt.Coffee_Shop.order.dtos;

import java.util.List;

public record OrderDTO(
        String id,
        String paymentMethod,
        double amount,
        String note,
        String orderDate,
        String deliveryType,
        String address,
        List<OrderDetailDTO> details,
        String customerId,
        String employeeId,
        String status,
        String receivePerson,
        String receivePhoneNumber
) {
}
