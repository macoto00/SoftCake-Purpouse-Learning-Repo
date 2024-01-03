package com.example.cucumberdemo.integration;

import com.example.cucumberdemo.controllers.AppController;
import com.example.cucumberdemo.models.userDTOs.CreateUserDTO;
import com.example.cucumberdemo.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppControllerIntegrationTest {

    private AppController appController;
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        userService = mock(UserServiceImpl.class);
        appController = new AppController(userService);
    }

    @Test
    public void should_CreateUser_When_ValidData() {
        CreateUserDTO createUserDTO = new CreateUserDTO("John", "Doe", "john@example.com");
        doNothing().when(userService).createUser(createUserDTO);

        ResponseEntity<?> responseEntity = appController.createUser(createUserDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created successfully", responseEntity.getBody());
        verify(userService, times(1)).createUser(createUserDTO);
    }

    @Test
    public void should_FailToCreateUser_When_ValidData() {
        CreateUserDTO createUserDTO = new CreateUserDTO("John", "Doe", "john@example.com");
        String errorMessage = "Failed to create user";
        doThrow(new RuntimeException(errorMessage)).when(userService).createUser(createUserDTO);

        ResponseEntity<?> responseEntity = appController.createUser(createUserDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
        verify(userService, times(1)).createUser(createUserDTO);
    }

    // Similarly, more tests for other endpoints: updateUser, getAllUsers, getOneUser, deleteUser
}
