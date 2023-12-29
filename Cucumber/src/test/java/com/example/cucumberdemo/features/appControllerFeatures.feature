Feature: App Controller Functionality

  Scenario: Create user with valid data
    Given a user creation request with valid data
    When the create user endpoint is called
    Then the user creation should be successful

  Scenario: Update user with valid data
    Given an update user request with valid data
    When the update user endpoint is called
    Then the user should be updated successfully

  Scenario: Delete user
    Given a delete user request
    When the delete user endpoint is called
    Then the user should be deleted successfully

  Scenario: Get all users
    Given a request to get all users
    When the get all users endpoint is called
    Then all users should be returned successfully

  Scenario: Get user by ID
    Given a request to get a user by ID
    When the get one user endpoint is called
    Then the user should be returned successfully
