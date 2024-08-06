package vn.com.ltdt.Coffee_Shop.utils.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.order.Order;
import vn.com.ltdt.Coffee_Shop.order.OrderDetail;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDetailDTO;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getPaymentMethod().name(),
                order.getAmount(),
                order.getNote(),
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")),
                order.getDeliveryType().name(),
                order.getAddress(),
                order.getDetails().stream().map(this::mapToDetailDTO).toList(),
                order.getCustomer().getId(),
                order.getEmployee() != null ? order.getEmployee().getId() : "Unknown",
                order.getStatus().name(),
                order.getReceivePerson(),
                order.getReceivePhoneNumber()
        );
    }

    public OrderDetailDTO mapToDetailDTO(OrderDetail detail) {
        return new OrderDetailDTO(
                productMapper.mapToDetailDTO(detail.getProductDetail()),
                productMapper.mapToDTO(detail.getProduct()),
                detail.getQuantity(),
                detail.getPrice()
        );
    }

}
