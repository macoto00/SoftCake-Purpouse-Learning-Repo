package com.example.cucumberdemo.stepdefinitions;

import com.example.cucumberdemo.controllers.AppController;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;
import com.example.cucumberdemo.services.UserServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AppControllerStepDefinitions {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private AppController appController;

    private UserCreateDTO userCreateDTO;
    private UpdateUserDTO updateUserDTO;
    private User user;

    // Step definitions for create user scenario

    @Given("a user creation request with valid data")
    public void aUserCreationRequestWithValidData() {
        // Mock valid user creation data
        userCreateDTO = UserCreateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
    }

    @When("the create user endpoint is called")
    public void theCreateUserEndpointIsCalled() {
        // Mock UserServiceImpl behavior when createUser is called (assuming createUser returns void)
        doNothing().when(userService).createUser(userCreateDTO); // Mocking a void method
        when(appController.createUser(userCreateDTO)).thenCallRealMethod();
    }

    @Then("the user creation should be successful")
    public void theUserShouldBeCreatedSuccessfully() {
        // Call the createUser method in the controller and assert the response
        ResponseEntity<?> responseEntity = appController.createUser(userCreateDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created successfully", responseEntity.getBody());
    }

    // Step definitions for update user scenario

    @Given("an update user request with valid data")
    public void anUpdateUserRequestWithValidData() {
        // Mock valid user update data
        updateUserDTO = UpdateUserDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();
    }

    @When("the update user endpoint is called")
    public void theUpdateUserEndpointIsCalled() {
        // Mock UserServiceImpl behavior when updateUser is called (assuming updateUser returns void)
        doNothing().when(userService).updateUser(updateUserDTO, 1L); // Mocking a void method
        when(appController.updateUser(updateUserDTO, 1L)).thenCallRealMethod();
    }

    @Then("the user should be updated successfully")
    public void theUserShouldBeUpdatedSuccessfully() {
        // Call the updateUser method in the controller and assert the response
        ResponseEntity<?> responseEntity = appController.updateUser(updateUserDTO, 1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User updated successfully", responseEntity.getBody());
    }

    // Step definitions for delete user scenario

    @Given("a delete user request")
    public void aDeleteUserRequest() {
        // No need for specific mock data in this case
    }

    @When("the delete user endpoint is called")
    public void theDeleteUserEndpointIsCalled() {
        // Mock UserServiceImpl behavior when deleteUser is called
        doNothing().when(userService).deleteUser(1L); // Mock success for user ID 1
        when(appController.deleteUser(1L)).thenCallRealMethod();
    }

    @Then("the user should be deleted successfully")
    public void theUserShouldBeDeletedSuccessfully() {
        // Call the deleteUser method in the controller and assert the response
        ResponseEntity<?> responseEntity = appController.deleteUser(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody());
    }

    // Step definitions for get all users scenario

    @Given("a request to get all users")
    public void aRequestToGetAllUsers() {
        // No need for specific mock data in this case
    }

    @When("the get all users endpoint is called")
    public void theGetAllUsersEndpointIsCalled() {
        // Mock UserServiceImpl behavior when getAllUsers is called
        List<User> userList = new ArrayList<>(); // Mock list of users
        when(userService.getAllUsers()).thenReturn(userList);
        when(appController.getAllUsers()).thenCallRealMethod();
    }

    @Then("all users should be returned successfully")
    public void allUsersShouldBeReturnedSuccessfully() {
        // Call the getAllUsers method in the controller and assert the response
        List<User> expectedUsers = new ArrayList<>(); // Define expected users
        ResponseEntity<?> responseEntity = appController.getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUsers, responseEntity.getBody());
    }

    // Step definitions for get one user scenario

    @Given("a request to get a user by ID")
    public void aRequestToGetAUserById() {
        // No need for specific mock data in this case
    }

    @When("the get one user endpoint is called")
    public void theGetOneUserEndpointIsCalled() {
        // Mock UserServiceImpl behavior when getOneUser is called
        user = new User(); // Mock a user
        when(userService.getOneUser(1L)).thenReturn(user); // Mock user with ID 1
        when(appController.getOneUser(1L)).thenCallRealMethod();
    }

    @Then("the user should be returned successfully")
    public void theUserShouldBeReturnedSuccessfully() {
        // Call the getOneUser method in the controller and assert the response
        ResponseEntity<?> responseEntity = appController.getOneUser(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }
}
