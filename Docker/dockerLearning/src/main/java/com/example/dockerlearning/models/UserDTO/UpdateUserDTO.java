package com.example.dockerlearning.models.UserDTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @NonNull
    private String username;
    @Nullable
    private String password;
    @Nullable
    private String firstname;
    @Nullable
    private String lastname;
}
