Feature: User Management

  Background:
    Given the database is initialized with three users
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
      | Jane      | Smith    | jane@example.com    |
      | Alice     | Johnson  | alice@example.com   |

  Scenario: Create a new user with unique details
    Given I have a user with details
      | firstName | lastName | email                |
      | George    | Washington| george@example.com  |
    When I create the user
    Then the user should be created successfully

  Scenario: Update user information
    Given I have a user with details
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
    And I update the user with ID "2"
      | firstName | lastName | email            |
      | Jane      | Smith    | jane@example.com |
    Then the user information should be updated successfully

  Scenario: Get all users
    Given I have the following users
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
      | Jane      | Smith    | jane@example.com    |
      | Alice     | Johnson  | alice@example.com   |
    When I retrieve all users
    Then all users should be returned successfully

  Scenario: Get user by ID
    Given I have a user with details
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
    When I retrieve the user with ID "1"
    Then the user should be returned successfully

  Scenario: Delete user
    Given I have the following users
      | firstName | lastName | email               |
      | John      | Doe      | john@example.com    |
      | Jane      | Smith    | jane@example.com    |
      | Alice     | Johnson  | alice@example.com   |
    When I delete the user with ID "1"
    Then the user should be deleted successfully
