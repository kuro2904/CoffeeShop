package vn.com.ltdt.Coffee_Shop.order;

import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO order);
    OrderDTO updateOrder(OrderDTO order);
    List<OrderDTO> getAllOrders();

}
