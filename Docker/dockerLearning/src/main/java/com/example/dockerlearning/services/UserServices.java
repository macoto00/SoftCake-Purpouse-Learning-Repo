package com.example.dockerlearning.services;

import com.example.dockerlearning.models.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    User createUser(User user);

    Optional<User> getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);
}

