package kr.co.e8ight.management.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "orders"
)
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "order_id",
            nullable = false
    )
    private int id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;
    private LocalDateTime orderDate;

    public Order() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public static Order createOrder(User user, Product product) {
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}
