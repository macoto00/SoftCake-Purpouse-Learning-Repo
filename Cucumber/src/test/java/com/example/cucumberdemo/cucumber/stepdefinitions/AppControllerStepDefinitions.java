package com.example.cucumberdemo.cucumber.stepdefinitions;

import com.example.cucumberdemo.controllers.AppController;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.CreateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.repositories.UserRepository;
import com.example.cucumberdemo.services.UserServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.persistence.EntityNotFoundException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppControllerStepDefinitions {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private AppController appController;
    private CreateUserDTO createUserDTO;
    private UpdateUserDTO updateUserDTO;
    private ResponseEntity<?> responseEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        appController = new AppController(userService);
    }

    // Step Definitions:

    // TODO:
    //  1) improve tests
    //  2) more robust
    //  3) more precise
    //  4) more use cases

    // Creating a user with valid data in App Controller

    @Given("a valid user creation request is sent to the App Controller")
    public void aUserCreationRequestWithValidDataAppController() {
        createUserDTO = CreateUserDTO.builder()
                .firstName("Mark")
                .lastName("Doe")
                .email("mark@example.com")
                .build();
    }

    @When("the create user endpoint is called")
    public void theCreateUserEndpointIsCalledAppController() {
        responseEntity = appController.createUser(createUserDTO);
    }

    @Then("the user creation should be successful in App Controller")
    public void theUserShouldBeCreatedSuccessfullyAppController() {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created successfully", responseEntity.getBody());
    }

    // Updating user details with valid data in App Controller

    @Given("an update user request with valid data is initiated in App Controller")
    public void anUpdateUserRequestWithValidDataAppController() {
        updateUserDTO = UpdateUserDTO.builder()
                .firstName("Johny")
                .lastName("Doe")
                .email("johny@example.com")
                .build();
    }

    @When("the update user endpoint is called")
    public void theUpdateUserEndpointIsCalledAppController() {
        responseEntity = appController.updateUser(updateUserDTO, 1L);
    }

    @Then("the user should be updated successfully in App Controller")
    public void theUserShouldBeUpdatedSuccessfullyAppController() {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User updated successfully", responseEntity.getBody());
    }

    // Retrieving all users when the list is not empty in App Controller

    @When("the get all users endpoint is called with a non-empty list")
    public void aRequestToGetAllUsersIsCalledWithNonEmptyListAppController() {
        List<User> notEmptyList = new ArrayList<>();
        var user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
        notEmptyList.add(user);
        when(userService.getAllUsers()).thenReturn(notEmptyList);
        responseEntity = appController.getAllUsers();
    }

    @Then("all users should be returned successfully in App Controller")
    public void allUsersShouldBeReturnedSuccessfullyAppController() {
        assert responseEntity.getBody() != null;
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    // Retrieving all users when the list is empty in App Controller

    @When("the get all users endpoint is called with an empty list")
    public void aRequestToGetAllUsersIsCalledWithEmptyListAppController() {
        List<User> emptyList = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(emptyList);
        responseEntity = appController.getAllUsers();
    }

    @Then("no users should be found in App Controller")
    public void noUsersShouldBeFoundAppController() {
        assertEquals("No users found", responseEntity.getBody());
    }

    @Then("the response status should be 404 in App Controller")
    public void theResponseStatusShouldBe404() {
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    // Retrieving a single user in App Controller

    @When("the get one endpoint is called to retrieve a user by ID {long}")
    public void theGetOneUserEndpointIsCalledWhenUserExistsAppController(Long userId) {
        var user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
        when(userService.getOneUser(userId)).thenReturn(user);
        responseEntity = appController.getOneUser(userId);
    }

    @Then("the user should be returned successfully in App Controller")
    public void theUserShouldBeReturnedSuccessfullyAppController() {
        var expectedUser = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
        var responseBody = (User) responseEntity.getBody();

        assertNotNull(responseBody);
        assertEquals(expectedUser.getFirstName(), responseBody.getFirstName());
        assertEquals(expectedUser.getLastName(), responseBody.getLastName());
        assertEquals(expectedUser.getEmail(), responseBody.getEmail());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // Retrieving a non-existing user in App Controller

    @When("the get one endpoint is called to retrieve a user by ID {long} that does not exist")
    public void theGetOneUserEndpointIsCalledWhenNoUserExistsAppController(Long userId) {
        when(userRepository.getUserByIdOrThrow(userId))
                .thenThrow(new EntityNotFoundException("Entity with id " + userId + " not found"));
        doThrow(EntityNotFoundException.class).when(userService).getOneUser(1L);
        responseEntity = appController.getOneUser(1L);
    }

    @Then("no user should one be found for getting one in App Controller")
    public void noUserShouldBeFoundWhenCallingTheGetOneUserEndpointAndUserDoNotExistAppController() {
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }

    // Deleting an existing user in App Controller

    @When("the delete user endpoint is called for an existing user with ID {long}")
    public void theDeleteUserEndpointIsCalledWhenUserExistsAppController(Long userId) {
        when(userRepository.getUserByIdOrThrow(userId))
                .thenReturn(new User(userId, "John", "Doe", "john@example.com"));
        responseEntity = appController.deleteUser(1L);
    }

    @Then("the user should be deleted successfully in App Controller")
    public void theUserShouldBeDeletedSuccessfullyWhenUserExistsAppController() {
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody());
    }

    // Deleting a non-existing user in App Controller

    @When("the delete user endpoint is called for a non-existing user with ID {long}")
    public void theDeleteUserEndpointIsCalledWhenUserDoNotExistAppController(Long userId) {
        when(userRepository.getUserByIdOrThrow(userId))
                .thenThrow(new EntityNotFoundException("Entity with id " + userId + " not found"));
        doThrow(EntityNotFoundException.class).when(userService).deleteUser(1L);
        responseEntity = appController.deleteUser(1L);
    }

    @Then("no user should be found for deletion in App Controller")
    public void noUserShouldBeFoundWhenUserDoNotExistAppController() {
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }
}
