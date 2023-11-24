package com.example.liquibasedemoprojecttwo.content.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String content;
    private UserDTO user;
}
