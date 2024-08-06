package vn.com.ltdt.Coffee_Shop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;
import vn.com.ltdt.Coffee_Shop.order.enums.DeliveryType;
import vn.com.ltdt.Coffee_Shop.order.enums.OrderStatus;
import vn.com.ltdt.Coffee_Shop.order.enums.PaymentMethod;
import vn.com.ltdt.Coffee_Shop.product.Product;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductDetailRepository;
import vn.com.ltdt.Coffee_Shop.product.repositories.ProductRepository;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.repository.CustomerRepository;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;
import vn.com.ltdt.Coffee_Shop.utils.mappers.OrderMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
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
    public Boolean createOrder(OrderDTO req) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            Customer customer = customerRepository.findById(req.customerId()).orElseThrow(
                    () -> new ResourceNotFound("Cannot found User", "id", req.customerId())
            );
            Order orderTemp = new Order();
            orderTemp.setCustomer(customer);
            orderTemp.setAddress(req.address());
            orderTemp.setNote(req.note());
            orderTemp.setPaymentMethod(PaymentMethod.valueOf(req.paymentMethod()));
            orderTemp.setOrderDate(LocalDateTime.parse(req.orderDate(), formatter));
            orderTemp.setDeliveryType(DeliveryType.valueOf(req.deliveryType()));
            orderTemp.setStatus(OrderStatus.valueOf(req.status()));
            orderTemp.setReceivePerson(req.receivePerson());
            orderTemp.setReceivePhoneNumber(req.receivePhoneNumber());
            orderTemp.setAmount(req.amount());

            Order savedOrder = orderRepository.save(orderTemp);

            List<OrderDetail> details = new ArrayList<>();
            req.details().forEach(orderDetailDTO -> {
                Product product = productRepository.findById(orderDetailDTO.product().id()).orElseThrow(
                        () -> new ResourceNotFound("Cannot found Product", "Id", String.valueOf(orderDetailDTO.product()))
                );
                ProductDetail productDetail = productDetailRepository.findById(orderDetailDTO.productDetail().id()).orElseThrow(
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
            Order finalSavedOrder = orderRepository.save(savedOrder);
            customer.getOrderHistories().add(finalSavedOrder);
            customerRepository.save(customer);
            OrderDTO orderDTO = orderMapper.mapToDTO(finalSavedOrder);
            log.info("OrderDTO after save {}", orderDTO.toString());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO req) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders(String userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFound("Cannot found User", "id", userId)
        );
        return orderRepository.findByCustomer(customer).stream().map(orderMapper::mapToDTO).toList();
    }
}
