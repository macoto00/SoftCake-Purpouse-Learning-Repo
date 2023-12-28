package com.example.cucumberdemo.services;

import com.example.cucumberdemo.mappers.UserMapper;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;
import com.example.cucumberdemo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TODO:
    //  1) improve tests
    //  2) add edge cases

    @Test
    public void should_CreateUser_When_ValidData() {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        // Mock the repository behavior for the email not existing
        when(userRepository.existsUserByEmail(anyString())).thenReturn(false);

        // Use ArgumentCaptor to capture the saved user argument
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        // Mock the repository's save method to capture the user argument
        when(userRepository.save(userCaptor.capture())).thenReturn(null);

        userService.createUser(userCreateDTO);

        // Verify if the userRepository's save method is invoked
        verify(userRepository, times(1)).save(any(User.class));

        // Retrieve the captured user and assert its fields with the DTO
        User savedUser = userCaptor.getValue();
        assertEquals(userCreateDTO.getFirstName(), savedUser.getFirstName());
        assertEquals(userCreateDTO.getLastName(), savedUser.getLastName());
        assertEquals(userCreateDTO.getEmail(), savedUser.getEmail());
    }

    @Test
    public void should_UpdateUser_When_ValidData() {
        UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        User existingUser = User.builder()
                .id(1L)
                .firstName("Existing")
                .lastName("User")
                .email("existing@example.com")
                .build();

        // Mocking repository behavior
        when(userRepository.getUserByIdOrThrow(1L)).thenReturn(existingUser);
        when(userRepository.existsUserByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        userService.updateUser(updateUserDTO, 1L);

        // Verify if the userRepository's save method is invoked
        verify(userRepository, times(1)).save(any(User.class));

        // Verify if the userMapper's updateUserFromDTO method is invoked with the correct arguments
        verify(userMapper, times(1)).updateUserFromDTO(eq(updateUserDTO), eq(existingUser));
    }

    @Test
    public void should_GetAllUsers_When_UsersExist() {
        List<User> userList = new ArrayList<>(); // Mock list of users
        User user1 = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
        User user2 = User.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .email("jane@example.com")
                .build();
        userList.add(user1);
        userList.add(user2);

        // Mocking repository behavior
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        // Assertions
        assertEquals(userList.size(), result.size());
        assertEquals(userList.get(0).getEmail(), result.get(0).getEmail());
        assertEquals(userList.get(1).getEmail(), result.get(1).getEmail());
    }

    @Test
    public void should_GetOneUser_When_UserExists() {
        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        // Mocking repository behavior
        when(userRepository.getUserByIdOrThrow(1L)).thenReturn(user);

        User result = userService.getOneUser(1L);

        // Assertions
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void should_DeleteUser_When_UserExists() {
        User existingUser = User.builder()
                .id(1L)
                .firstName("Existing")
                .lastName("User")
                .email("existing@example.com")
                .build();

        // Mocking repository behavior
        when(userRepository.getUserByIdOrThrow(1L)).thenReturn(existingUser);
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        // Verify if the userRepository's deleteById method is invoked
        verify(userRepository, times(1)).deleteById(1L);
    }
}
