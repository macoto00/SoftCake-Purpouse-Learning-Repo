package com.example.dockerlearning.services;

import com.example.dockerlearning.models.User;
import com.example.dockerlearning.models.UserDTO.RegisterUserDTO;
import com.example.dockerlearning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(RegisterUserDTO registerUserDTO) {
        User newUser = new User();
        newUser.setUsername(registerUserDTO.getUsername());
        newUser.setPassword(registerUserDTO.getPassword());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}
