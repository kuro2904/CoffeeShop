package vn.com.ltdt.Coffee_Shop.utils.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.order.Order;
import vn.com.ltdt.Coffee_Shop.order.OrderDetail;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDetailDTO;

@Service
public class OrderMapper {

    public OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getPaymentMethod(),
                order.getAmount(),
                order.getNote(),
                order.getOrderDate(),
                order.getDeliveryType(),
                order.getAddress(),
                order.getDetails().stream().map(this::mapToDetailDTO).toList(),
                order.getCustomer().getId(),
                order.getEmployee() != null ? order.getEmployee().getId() : "Unknown",
                order.getStatus(),
                order.getReceivePerson(),
                order.getReceivePhoneNumber()
        );
    }

    public OrderDetailDTO mapToDetailDTO(OrderDetail detail) {
        return new OrderDetailDTO(
                detail.getProductDetail().getId(),
                detail.getProduct().getId(),
                detail.getQuantity(),
                detail.getPrice()
        );
    }

}
