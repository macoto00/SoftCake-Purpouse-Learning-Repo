package com.example.liquibasedemoprojecttwo.content.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private List<PostDTO> posts;
}
