package com.example.postgresqlconnection.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
