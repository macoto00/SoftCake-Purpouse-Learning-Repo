package com.example.cucumberdemo.stepdefinitions;

import com.example.cucumberdemo.config.DatabaseFiller;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UserCreateDTO;
import com.example.cucumberdemo.repositories.UserRepository;
import com.example.cucumberdemo.services.UserServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

public class UserStepDefinitions {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private UserCreateDTO userCreateDTO;
    private UpdateUserDTO updateUserDTO;
    private User createdUser;
    private List<User> allUsers;

    @Before
    public void setUp() {
        userService = Mockito.mock(UserServiceImpl.class);
    }

    // Background: Initialize the database with three users

    @Given("the database is initialized with three users")
    public void initializeDatabaseWithThreeUsers() {
        DatabaseFiller databaseFiller = new DatabaseFiller(userRepository);
        databaseFiller.run();
    }

    // Scenario: Create a new user with unique details

    @Given("I have a user with details")
    public void iHaveAUserWithDetails(Map<String, String> userDetails) {
        userCreateDTO = UserCreateDTO.builder()
                .firstName(userDetails.get("firstName"))
                .lastName(userDetails.get("lastName"))
                .email(userDetails.get("email"))
                .build();
    }

    @When("I create the user")
    public void iCreateTheUser() {
        Mockito.doNothing().when(userService).createUser(Mockito.any(UserCreateDTO.class));
        userService.createUser(userCreateDTO);
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(createdUser);
        createdUser = userRepository.findByEmail(userCreateDTO.getEmail());
    }

    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        Assert.assertNotNull("User should be created", createdUser);
        Assert.assertEquals(userCreateDTO.getEmail(), createdUser.getEmail());
    }

    // Scenario: Update user information

    @Given("I have a user with details")
    public void iHaveAUserForUpdate(Map<String, String> userDetails) {
        updateUserDTO = UpdateUserDTO.builder()
                .firstName(userDetails.get("firstName"))
                .lastName(userDetails.get("lastName"))
                .email(userDetails.get("email"))
                .build();
    }

    @Given("the user with ID {string} exists")
    public void theUserWithIdExists(String userId) {
        createdUser = userRepository.findById(Long.parseLong(userId)).orElse(null);
    }

    @When("I update the user with ID {string}")
    public void iUpdateTheUserWithID(String userId) {
        userService.updateUser(updateUserDTO, Long.parseLong(userId));
    }

    @Then("the user information should be updated successfully")
    public void theUserInformationShouldBeUpdatedSuccessfully() {
        Assert.assertNotNull("User should be updated", createdUser);
        Assert.assertEquals(updateUserDTO.getEmail(), createdUser.getEmail());
    }

    // Scenario: Get all users

    @Given("I have the following users")
    public void theFollowingUsersExist(List<User> users) {
        allUsers = users;
    }

    @When("I retrieve all users")
    public void iRetrieveAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(allUsers);
        allUsers = userService.getAllUsers();
    }

    @Then("all users should be returned successfully")
    public void allUsersShouldBeReturnedSuccessfully() {
        Assert.assertNotNull("User list should not be null", allUsers);
    }

    // Scenario: Get user by ID

    @Given("the user with ID {string} exists")
    public void theUserWithIdExistsForRetrieve(String userId) {
        createdUser = userRepository.findById(Long.parseLong(userId)).orElse(null);
    }

    @When("I retrieve the user with ID {string}")
    public void iRetrieveTheUserWithID(String userId) {
        Mockito.when(userService.getOneUser(Mockito.anyLong())).thenReturn(createdUser);
        createdUser = userService.getOneUser(Long.parseLong(userId));
    }

    @Then("the user should be returned successfully")
    public void theUserShouldBeReturnedSuccessfully() {
        Assert.assertNotNull("User should be returned", createdUser);
    }

    // Scenario: Delete user

    @Given("I have the following users")
    public void theFollowingUsersExistForDeletion(List<User> users) {
        allUsers = users;
    }

    @When("I delete the user with ID {string}")
    public void iDeleteTheUserWithID(String userId) {
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyLong());
        userService.deleteUser(Long.parseLong(userId));
    }

    @Then("the user should be deleted successfully")
    public void theUserShouldBeDeletedSuccessfully() {
        Mockito.verify(userService, Mockito.times(1)).deleteUser(Mockito.anyLong());
        Mockito.verify(userService, Mockito.times(1)).getOneUser(Mockito.anyLong());
        Assert.assertNull("User should be deleted", userService.getOneUser(createdUser.getId()));
    }
}
