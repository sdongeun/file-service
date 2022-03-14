package kr.co.e8ight.management.service;

import kr.co.e8ight.management.domain.entity.User;

public interface UserService {
    User addUser(User user);

    User getUser(int id);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
