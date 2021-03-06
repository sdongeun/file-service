package kr.co.e8ight.management.service.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import kr.co.e8ight.management.domain.entity.Order;
import kr.co.e8ight.management.domain.entity.Product;
import kr.co.e8ight.management.domain.entity.User;
import kr.co.e8ight.management.domain.repository.OrderRepository;
import kr.co.e8ight.management.domain.repository.ProductRepository;
import kr.co.e8ight.management.domain.repository.UserRepository;
import kr.co.e8ight.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void makeOrder(User user, int productId) {
        Optional<Product> product = this.productRepository.findById(productId);
        product.ifPresent((selectProduct) -> {
            Order order = Order.createOrder(user, selectProduct);
            this.orderRepository.save(order);
        });
    }
}
