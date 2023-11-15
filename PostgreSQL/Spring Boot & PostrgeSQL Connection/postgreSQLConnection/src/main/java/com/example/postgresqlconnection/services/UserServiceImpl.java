package com.example.postgresqlconnection.services;

import com.example.postgresqlconnection.models.User;
import com.example.postgresqlconnection.models.dtos.CreateUserDTO;
import com.example.postgresqlconnection.models.dtos.UpdateUserDTO;
import com.example.postgresqlconnection.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            userToUpdate.setUsername(updateUserDTO.getUsername());
            userToUpdate.setPassword(updateUserDTO.getPassword());
            userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found for id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User userToDelete = optionalUser.get();
            userRepository.delete(userToDelete);
        } else {
            throw new RuntimeException("User not found for id: " + id);
        }
    }
}
