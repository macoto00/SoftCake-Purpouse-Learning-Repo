Feature: User Management Functionality

  # Create user
  Scenario: Creating a new user with valid data in User Management
    Given a valid user creation request is sent to the User Management
    When the create user method is called
    Then the user should be created successfully in User Management

  # Update user
  Scenario: Updating user details with valid data in User Management
    Given an update user request with valid data is initiated in User Management
    When the update user method is called
    Then the user details should be updated successfully in User Management

  # Get all users
  Scenario: Retrieving all users when the list is not empty in User Management
    When the get all users method is called with a non-empty list in User Management
    Then all users should be retrieved successfully in User Management

  # Get one user
  Scenario: Retrieving a single user in User Management
    When the get one method is called to retrieve a user by ID 1 in User Management
    Then the user should be retrieved successfully in User Management

  # Delete user
  Scenario: Deleting an existing user in User Management
    When the delete user method is called for an existing user with ID 1 in User Management
    Then the user should be deleted successfully in User Management
