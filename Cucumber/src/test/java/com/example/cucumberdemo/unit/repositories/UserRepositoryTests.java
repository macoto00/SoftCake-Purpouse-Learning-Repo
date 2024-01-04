package com.example.cucumberdemo.unit.repositories;

import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTests {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_SaveUser_When_ProvidedValidData() {
        // Given
        User newUser = new User(null, "John", "Doe", "john@example.com");

        // When
        when(userRepository.save(newUser)).thenReturn(new User(1L, "John", "Doe", "john@example.com"));

        // Perform the save operation
        User savedUser = userRepository.save(newUser);

        // Then
        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("john@example.com", savedUser.getEmail());

        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void should_TestsExistence_When_ExistUserByEmail() {
        // Given
        String existingEmail = "existing@example.com";

        // When
        when(userRepository.existsUserByEmail(existingEmail)).thenReturn(true);

        // Perform the existence check
        boolean exists = userRepository.existsUserByEmail(existingEmail);

        // Then
        assertTrue(exists);

        verify(userRepository, times(1)).existsUserByEmail(existingEmail);
    }

    @Test
    void should_FetchUserByEmail_When_ProvidedValidEmail() {
        // Given
        String email = "john@example.com";
        User john = new User(1L, "John", "Doe", email);

        // When
        when(userRepository.findByEmail(email)).thenReturn(john);

        // Perform the fetch operation
        User foundUser = userRepository.findByEmail(email);

        // Then
        assertNotNull(foundUser);
        assertEquals(john.getId(), foundUser.getId());
        assertEquals(john.getEmail(), foundUser.getEmail());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void should_ThrowEntityExistsException_When_EmailAlreadyExistsForDifferentUser() {
        // Given
        String existingEmail = "existing@example.com";

        User existingUser = new User(1L, "Existing", "User", existingEmail);
        when(userRepository.existsUserByEmail(existingEmail)).thenReturn(true);
        when(userRepository.findByEmail(existingEmail)).thenReturn(existingUser);

        // When - Then
        assertThrows(EntityExistsException.class,
                () -> userRepository.checkIfExistByEmailAndThrow(existingEmail, 2L));

        verify(userRepository, times(1)).findByEmail(existingEmail);
        verify(userRepository, times(1)).existsUserByEmail(existingEmail);
    }

    @Test
    void should_GetUserByIdOrThrow_When_UserExistsWithGivenId() {
        // Given
        Long userId = 1L;
        User existingUser = new User(userId, "John", "Doe", "john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // When - Then
        User foundUser = userRepository.getUserByIdOrThrow(userId);

        assertNotNull(foundUser);
        assertEquals(existingUser.getId(), foundUser.getId());
        assertEquals(existingUser.getEmail(), foundUser.getEmail());
    }

    @Test
    void should_ThrowEntityNotFoundException_When_UserDoesNotExistWithGivenId() {
        // Given
        Long nonExistingUserId = 10L;
        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        // When - Then
        assertThrows(EntityNotFoundException.class,
                () -> userRepository.getUserByIdOrThrow(nonExistingUserId));
    }
}

