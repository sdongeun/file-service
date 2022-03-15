package kr.co.e8ight.management.service.impl;

import java.util.Optional;
import kr.co.e8ight.management.domain.entity.User;
import kr.co.e8ight.management.domain.repository.UserRepository;
import kr.co.e8ight.management.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(User user) {
        return (User)this.userRepository.save(user);
    }

    @Transactional(
            readOnly = true
    )
    public User getUser(int id) {
        Optional<User> user = this.userRepository.findById(id);
        return (User)user.get();
    }

    @Transactional
    public User updateUser(int id, User user) {
        Optional<User> updateUser = this.userRepository.findById(id);
        updateUser.ifPresent((selectUser) -> {
            selectUser.setName(user.getName());
            selectUser.setAccount(user.getAccount());
            selectUser.setPassword(user.getPassword());
            this.userRepository.save(selectUser);
        });
        return (User)updateUser.get();
    }

    @Transactional
    public void deleteUser(int id) {
        Optional<User> user = this.userRepository.findById(id);
        user.ifPresent((selectUser) -> {
            this.userRepository.delete(selectUser);
        });
    }
}
