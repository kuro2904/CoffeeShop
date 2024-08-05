package vn.com.ltdt.Coffee_Shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductDetailRepository;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductRepository;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.repository.CustomerRepository;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;
import vn.com.ltdt.Coffee_Shop.utils.mappers.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Override
    public OrderDTO createOrder(OrderDTO req) {
        Customer customer = customerRepository.findById(req.customerId()).orElseThrow(
                () -> new ResourceNotFound("Cannot found User", "id", req.customerId())
        );
        Order orderTemp = new Order();
        orderTemp.setCustomer(customer);
        orderTemp.setAddress(req.address());
        orderTemp.setNote(req.note());
        orderTemp.setPaymentMethod(req.paymentMethod());
        orderTemp.setOrderDate(req.orderDate());
        orderTemp.setDeliveryType(req.deliveryType());
        orderTemp.setStatus(req.status());
        orderTemp.setReceivePerson(req.receivePerson());
        orderTemp.setReceivePhoneNumber(req.receivePhoneNumber());
        orderTemp.setAmount(req.amount());

        Order savedOrder = orderRepository.save(orderTemp);

        List<OrderDetail> details = new ArrayList<>();
        req.details().forEach(orderDetailDTO -> {
            Product product = productRepository.findById(orderDetailDTO.product()).orElseThrow(
                    () -> new ResourceNotFound("Cannot found Product", "Id", String.valueOf(orderDetailDTO.product()))
            );
            ProductDetail productDetail = productDetailRepository.findById(orderDetailDTO.productDetail()).orElseThrow(
                    () -> new ResourceNotFound("Cannot found Product", "Id", String.valueOf(orderDetailDTO.productDetail()))
            );
            details.add(orderDetailRepository.save(OrderDetail
                    .builder()
                    .price(orderDetailDTO.price())
                    .quantity(orderDetailDTO.quantity())
                    .order(savedOrder)
                    .product(product)
                    .productDetail(productDetail)
                    .build()));

        });
        savedOrder.setDetails(details);
        return orderMapper.mapToDTO(
                orderRepository.save(savedOrder)
        );
    }

    @Override
    public OrderDTO updateOrder(OrderDTO req) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }
}
