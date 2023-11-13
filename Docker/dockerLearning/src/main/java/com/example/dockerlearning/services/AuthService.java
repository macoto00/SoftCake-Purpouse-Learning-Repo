package com.example.dockerlearning.services;

import com.example.dockerlearning.models.User;
import com.example.dockerlearning.models.UserDTO.RegisterUserDTO;

import java.util.Optional;

public interface AuthService {
    User registerUser(RegisterUserDTO registerUserDTO);
    Optional<User> loginUser(String username, String password);
}
