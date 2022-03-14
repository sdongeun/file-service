package kr.co.e8ight.management.service;

import kr.co.e8ight.management.domain.entity.User;

public interface OrderService {
    void makeOrder(User user, int productId);
}
