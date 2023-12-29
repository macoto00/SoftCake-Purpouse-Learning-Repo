package com.example.cucumberdemo.models.userDTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
}
