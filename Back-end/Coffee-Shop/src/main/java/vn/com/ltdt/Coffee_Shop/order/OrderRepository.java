package vn.com.ltdt.Coffee_Shop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.ltdt.Coffee_Shop.user.Customer;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomer(Customer customer);
}
