package com.example.liquibasedemoprojecttwo.content.services;

import com.example.liquibasedemoprojecttwo.content.models.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
}
