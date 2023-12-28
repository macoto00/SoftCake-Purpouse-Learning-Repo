package com.example.cucumberdemo.controllers;

import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;
import com.example.cucumberdemo.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private AppController appController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TODO:
    //  1) improve tests
    //  2) add tests for each case of response of the controllers (only successful ones are now)

    @Test
    public void should_CreateUser_When_ValidData() {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        doNothing().when(userService).createUser(userCreateDTO);

        ResponseEntity<?> responseEntity = appController.createUser(userCreateDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created successfully", responseEntity.getBody());
    }

    @Test
    public void should_UpdateUser_When_ValidData() {
        UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        doNothing().when(userService).updateUser(updateUserDTO, 1L);

        ResponseEntity<?> responseEntity = appController.updateUser(updateUserDTO, 1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User updated successfully", responseEntity.getBody());
    }

    @Test
    public void should_GetAllUsers_When_UsersExist() {
        List<User> userList = new ArrayList<>(); // Initialize mock user list
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
        userList.add(user);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<?> responseEntity = appController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());
    }

    @Test
    public void should_GetOneUser_When_UserExists() {
        User user = new User(); // Mock a user
        when(userService.getOneUser(1L)).thenReturn(user);

        ResponseEntity<?> responseEntity = appController.getOneUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void should_DeleteUser_When_UserExists() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<?> responseEntity = appController.deleteUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody());
    }
}
