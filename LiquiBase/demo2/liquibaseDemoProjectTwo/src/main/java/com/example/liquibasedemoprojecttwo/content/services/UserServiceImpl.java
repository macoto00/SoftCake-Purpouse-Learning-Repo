package com.example.liquibasedemoprojecttwo.content.services;

import com.example.liquibasedemoprojecttwo.content.models.Post;
import com.example.liquibasedemoprojecttwo.content.models.PostDTO;
import com.example.liquibasedemoprojecttwo.content.models.User;
import com.example.liquibasedemoprojecttwo.content.models.UserDTO;
import com.example.liquibasedemoprojecttwo.content.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTOWithPosts)
                .collect(Collectors.toList());
    }

    private UserDTO convertToUserDTOWithPosts(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setPosts(user.getPosts().stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList()));
        return userDTO;
    }
}

