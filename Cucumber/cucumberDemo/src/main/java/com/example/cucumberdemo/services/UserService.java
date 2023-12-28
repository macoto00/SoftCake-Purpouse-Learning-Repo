package com.example.cucumberdemo.services;

import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;

import java.util.List;

public interface UserService {
    void createUser(UserCreateDTO userCreateDTO);
    void updateUser(UpdateUserDTO updateUserDTO, Long id);
    List<User> getAllUsers();
    User getOneUser(Long id);
    void deleteUser(Long id);
}
