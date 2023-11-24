package com.example.dockerlearning.services;

import com.example.dockerlearning.models.User;
import com.example.dockerlearning.models.UserDTO.UpdateUserDTO;
import com.example.dockerlearning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String updateUser(UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(updateUserDTO.getUsername());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setFirstname(updateUserDTO.getFirstname());
            existingUser.setLastname(updateUserDTO.getLastname());
            existingUser.setUsername(updateUserDTO.getUsername());
            assert updateUserDTO.getPassword() != null;
            existingUser.setPassword(updateUserDTO.getPassword());

            userRepository.save(existingUser);
            return "User updated successfully";
        } else {
            return "Error updating user";
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
