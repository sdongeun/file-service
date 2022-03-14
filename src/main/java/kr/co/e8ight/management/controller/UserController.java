package kr.co.e8ight.management.controller;

import kr.co.e8ight.management.domain.entity.User;
import kr.co.e8ight.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api"})
public class UserController {
    @Autowired
    UserService service;

    public UserController() {
    }

    @PostMapping({"/join"})
    public String join(@RequestBody User user) {
        User newUser = this.service.addUser(user);
        return newUser.getName() + "님의 회원가입을 축하드립니다.";
    }

    @GetMapping({"/user"})
    public User findUser(@RequestParam int id) {
        return this.service.getUser(id);
    }

    @PutMapping({"/user"})
    public User changeInfo(@RequestParam int id, @RequestBody User user) {
        return this.service.updateUser(id, user);
    }

    @DeleteMapping({"/user"})
    public String delete(@RequestParam int id) {
        this.service.deleteUser(id);
        return "이용해주셔서 감사합니다.";
    }
}
