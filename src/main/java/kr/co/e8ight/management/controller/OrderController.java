package kr.co.e8ight.management.controller;

import kr.co.e8ight.management.domain.entity.User;
import kr.co.e8ight.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api"})
public class OrderController {
    @Autowired
    OrderService service;

    public OrderController() {
    }

    @PostMapping({"/order"})
    public void order(@RequestBody User user, @RequestParam int productId) {
        this.service.makeOrder(user, productId);
    }
}
