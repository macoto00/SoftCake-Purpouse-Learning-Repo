Feature: App Controller Functionality

  # Create user
  Scenario: Creating a user with valid data in App Controller
    Given a valid user creation request is sent to the App Controller
    When the create user endpoint is called
    Then the user creation should be successful in App Controller

  # Update user
  Scenario: Updating user details with valid data in App Controller
    Given an update user request with valid data is initiated in App Controller
    When the update user endpoint is called
    Then the user should be updated successfully in App Controller

  # Get all users
  Scenario: Retrieving all users when the list is not empty in App Controller
    When the get all users endpoint is called with a non-empty list
    Then all users should be returned successfully in App Controller

  Scenario: Retrieving all users when the list is empty in App Controller
    When the get all users endpoint is called with an empty list
    Then no users should be found in App Controller
    And the response status should be 404 in App Controller

  # Get one user
  Scenario: Retrieving a single user in App Controller
    When the get one endpoint is called to retrieve a user by ID 1
    Then the user should be returned successfully in App Controller

  Scenario: Retrieving a non-existing user in App Controller
    When the get one endpoint is called to retrieve a user by ID 1 that does not exist
    Then no user should one be found for getting one in App Controller

  # Delete user
  Scenario: Deleting an existing user in App Controller
    When the delete user endpoint is called for an existing user with ID 1
    Then the user should be deleted successfully in App Controller

  Scenario: Deleting a non-existing user in App Controller
    When the delete user endpoint is called for a non-existing user with ID 1
    Then no user should be found for deletion in App Controller
