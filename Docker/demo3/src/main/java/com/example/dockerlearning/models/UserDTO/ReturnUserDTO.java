package com.example.dockerlearning.models.UserDTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnUserDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @Nullable
    private String firstname;
    @Nullable
    private String lastname;
}
