package vn.com.ltdt.Coffee_Shop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ltdt.Coffee_Shop.order.dtos.OrderDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @PostMapping("create")
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderDTO order) {
        log.info("Creating order: {}", order);
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<OrderDTO>> getOrder(@PathVariable String userId) {
        log.info("Retrieving orders for user: {}", userId);
        return ResponseEntity.ok(orderService.getAllOrders(userId));
    }
}
