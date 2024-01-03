package com.example.cucumberdemo.cucumber.stepdefinitions;

import com.example.cucumberdemo.mappers.UserMapper;
import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.models.userDTOs.CreateUserDTO;
import com.example.cucumberdemo.models.userDTOs.UpdateUserDTO;
import com.example.cucumberdemo.repositories.UserRepository;
import com.example.cucumberdemo.services.UserServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserManagementStepDefinitions {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper;
    private CreateUserDTO createUserDTO;
    private UpdateUserDTO updateUserDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRepository = spy(UserRepository.class);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    // Step definitions:

    // TODO:
    //  1) improve tests
    //  2) more robust
    //  3) more precise
    //  4) more use cases

    // Scenario: Creating a new user with valid data in User Management

    @Given("a valid user creation request is sent to the User Management")
    public void a_user_creation_request_with_valid_data() {
        createUserDTO = CreateUserDTO.builder()
                .firstName("Mark")
                .lastName("Doe")
                .email("mark@example.com")
                .build();
    }

    @When("the create user method is called")
    public void the_create_user_method_is_called() {
        doNothing().when(userRepository).checkIfExistByEmailAndThrow(eq(createUserDTO.getEmail()), any());
        userService.createUser(createUserDTO);
    }

    @Then("the user should be created successfully in User Management")
    public void the_user_should_be_created_successfully() {
        verify(userRepository).checkIfExistByEmailAndThrow(createUserDTO.getEmail(), null);
        User expectedUser = User.builder()
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .email(createUserDTO.getEmail())
                .build();
        assertNotNull(expectedUser);
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository).save(argThat(savedUser ->
                savedUser.getFirstName().equals(expectedUser.getFirstName()) &&
                        savedUser.getLastName().equals(expectedUser.getLastName()) &&
                        savedUser.getEmail().equals(expectedUser.getEmail())
        ));
    }

    // Scenario: Updating user details with valid data in User Management

    @Given("an update user request with valid data is initiated in User Management")
    public void a_user_update_request_with_valid_data() {
    }

    @When("the update user method is called")
    public void the_update_user_method_is_called() {
    }

    @Then("the user details should be updated successfully in User Management")
    public void the_user_should_be_updated_successfully() {
    }

    // Scenario: Retrieving all users when the list is not empty in User Management

    @When("the get all users method is called with a non-empty list in User Management")
    public void the_get_all_method_is_called() {
    }

    @Then("all users should be retrieved successfully in User Management")
    public void the_users_should_be_retrieved_successfully() {
    }

    // Scenario: Retrieving a single user in User Management

    @When("the get one method is called to retrieve a user by ID 1 in User Management")
    public void the_get_one_method_is_called() {
    }

    @Then("the user should be retrieved successfully in User Management")
    public void the_user_should_be_retrieved_successfully() {
    }

    // Scenario: Deleting an existing user in User Management

    @When("the delete user method is called for an existing user with ID 1 in User Management")
    public void the_delete_method_is_called() {
    }

    @Then("the user should be deleted successfully in User Management")
    public void the_user_should_be_deleted_successfully() {
    }
}
