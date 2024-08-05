package vn.com.ltdt.Coffee_Shop.order.dtos;

import vn.com.ltdt.Coffee_Shop.order.enums.OrderStatus;
import vn.com.ltdt.Coffee_Shop.order.enums.DeliveryType;
import vn.com.ltdt.Coffee_Shop.order.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        String id,
        PaymentMethod paymentMethod,
        double amount,
        String note,
        LocalDateTime orderDate,
        DeliveryType deliveryType,
        String address,
        List<OrderDetailDTO> details,
        String customerId,
        String employeeId,
        OrderStatus status,
        String receivePerson,
        String receivePhoneNumber
) {
}
