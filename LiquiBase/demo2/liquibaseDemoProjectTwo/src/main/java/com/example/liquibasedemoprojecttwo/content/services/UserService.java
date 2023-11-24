package com.example.liquibasedemoprojecttwo.content.services;

import com.example.liquibasedemoprojecttwo.content.models.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
}
