# user_management.feature

Feature: User Management

  Scenario: Create a new user
    Given I have a user with details
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
    When I create the user
    Then the user should be created successfully

  Scenario: Update user information
    Given I have an existing user with ID "123"
    When I update the user details
      | firstName | lastName | email            |
      | Jane      | Smith    | jane@example.com |
    Then the user information should be updated successfully

