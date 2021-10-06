package org.deegaem.imtusers.service;

import org.deegaem.imtusers.domain.User;
import org.deegaem.imtusers.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        User userFromDb = userRepository.findById(id).get();
        System.out.println(userFromDb.toString());
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        //userFromDb.setCredential(User.getCredential());
        userRepository.save(userFromDb);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
