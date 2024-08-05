package vn.com.ltdt.Coffee_Shop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @PostMapping("create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
        log.info("Creating order: {}", order);
        return ResponseEntity.ok(orderService.createOrder(order));
    }

}
