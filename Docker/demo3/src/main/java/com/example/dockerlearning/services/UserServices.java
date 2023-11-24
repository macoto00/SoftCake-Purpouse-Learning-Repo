package com.example.dockerlearning.services;

import com.example.dockerlearning.models.User;
import com.example.dockerlearning.models.UserDTO.UpdateUserDTO;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    String updateUser(UpdateUserDTO updateUserDTO);

    void deleteUser(Long userId);
}

