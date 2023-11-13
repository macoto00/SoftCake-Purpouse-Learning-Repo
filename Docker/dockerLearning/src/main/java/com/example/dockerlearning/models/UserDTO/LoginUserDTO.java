package com.example.dockerlearning.models.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
