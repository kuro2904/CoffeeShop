package vn.com.ltdt.Coffee_Shop.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.ltdt.Coffee_Shop.product.ProductDetail;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @NotNull(message = "The price cannot be null")
    private double price;
    @NotNull(message = "The order cannot be null")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}
