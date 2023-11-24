package com.example.liquibasedemoprojecttwo.content.services;

import com.example.liquibasedemoprojecttwo.content.models.Post;
import com.example.liquibasedemoprojecttwo.content.models.PostDTO;
import com.example.liquibasedemoprojecttwo.content.models.UserDTO;
import com.example.liquibasedemoprojecttwo.content.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        return posts.stream()
                .map(this::convertToPostDTOWithUser)
                .collect(Collectors.toList());
    }

    private PostDTO convertToPostDTOWithUser(Post post) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        postDTO.setUser(modelMapper.map(post.getUser(), UserDTO.class));
        return postDTO;
    }
}
