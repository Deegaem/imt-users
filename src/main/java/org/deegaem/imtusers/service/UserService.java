package org.deegaem.imtusers.service;

import org.deegaem.imtusers.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id);

    User insert(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long userId);
}
