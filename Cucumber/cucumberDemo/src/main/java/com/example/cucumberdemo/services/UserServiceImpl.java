package com.example.cucumberdemo.services;

import com.example.cucumberdemo.mappers.UserMapper;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;
import com.example.cucumberdemo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public void createUser(UserCreateDTO userCreateDTO) {
        String emailToCheck = userCreateDTO.getEmail();

        userRepository.checkIfExistByEmailAndThrow(emailToCheck, null);

        User newUser = User.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .email(userCreateDTO.getEmail())
                .build();

        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UpdateUserDTO updateUserDTO, Long id) {
        var userToUpdate = userRepository.getUserByIdOrThrow(id);

        userRepository.checkIfExistByEmailAndThrow(updateUserDTO.getEmail(), id);

        userMapper.updateUserFromDTO(updateUserDTO, userToUpdate);

        userRepository.save(userToUpdate);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(Long id) {
        return userRepository.getUserByIdOrThrow(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.getUserByIdOrThrow(id);
        userRepository.deleteById(id);
    }
}
