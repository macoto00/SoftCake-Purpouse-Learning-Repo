package com.example.postgresqlconnection.services;

import com.example.postgresqlconnection.models.User;
import com.example.postgresqlconnection.models.dtos.CreateUserDTO;
import com.example.postgresqlconnection.models.dtos.UpdateUserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(CreateUserDTO createUserDTO);
    Optional<User> getUser(Long id);
    List<User> getAllUsers();
    void updateUser(Long id, UpdateUserDTO updateUserDTO);
    void deleteUser(Long id);
}
