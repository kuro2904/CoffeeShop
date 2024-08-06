package vn.com.ltdt.Coffee_Shop.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import vn.com.ltdt.Coffee_Shop.order.enums.OrderStatus;
import vn.com.ltdt.Coffee_Shop.order.enums.DeliveryType;
import vn.com.ltdt.Coffee_Shop.order.enums.PaymentMethod;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.Employee;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order {
    @Id
    @UuidGenerator
    private String id;
    @NotNull(message = "The payment method cannot be null")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Amount cannot be null")
    private double amount;
    private String note;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @NotNull(message = "Address cannot be null")
    private String address;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;
    @NotNull(message = "The Customer cannot be null")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Employee employee;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String receivePerson;
    private String receivePhoneNumber;
}
