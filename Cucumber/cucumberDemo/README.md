# Demo App

Simple Demo App to showcase useful stuff. 

App has one Entity `User`. We can do basic CRUD operations with the entity like:

TODO: create a specification of how to proceed every operation

- create
- read one
- read all
- update
- delete

By starting the App, there will be 3 users created into the DB. 

## PostgresSQL

App is connected to PostgresSQL database. 

## Mapstruct

App is using mapping of objects.

## Testing

App should cover test types like Cucumber, Unit and Integration tests. 

**Unit tests** are focused on testing individual units of code, such as methods or classes, in isolation without external dependencies, verifying their behavior. 

**Cucumber tests**, following Behavior-Driven Development (BDD), aim to validate system behavior from an end-user perspective, using natural language scenarios (Given-When-Then) to test interactions between components. 

**Integration tests**, on the other hand, ensure seamless interactions between various parts of the system, examining how different components work together, often spanning across multiple layers or systems, detecting integration issues. 

Each test type serves a unique purpose: 

- unit tests for isolated unit validation 
- Cucumber tests for behavior verification
- integration tests for examining system-wide interactions

## Cucumber Testing

Cucumber is best suited for behavior-driven testing where the focus is on describing the behavior of the application in a language that is closer to natural language. It's often used to ensure that the expected behavior of the system, especially at the user interaction level, is correctly implemented.

### Step 1 - Add Cucumber dependencies

Include Cucumber dependencies along with the necessary plugins.

### Step 2 - Create Feature Files and Step Definition

Create .feature files in the src/test/resources directory and corresponding step definition classes in a designated package within the test source directory.

```bash
|-- java
  |-- com
    |-- example
      |-- cucumberdemo
        |-- CucumberDemoApplicationTests.java
        |-- controllers
          |-- AppControllerTest.java
        |-- features              <-- this line
          |-- appControllerFeatures.feature
          |-- userManagementFeatures.feature
        |-- integration
          |-- IntegrationTests.java
        |-- services
          |-- UserServiceTest.java
        |-- stepdefinitions       <-- this line
          |-- AppControllerStepDefinitions.java
          |-- UserStepDefinitions.java
```

Describe test scenarios using Gherkin syntax.

```gherkin
# Example feature file: user.feature

Feature: User Management

    Scenario: Create a new user
        Given I have a user with details
            | firstName | lastName | email               |
            | John      | Doe      | john@example.com    |
        When I create the user
        Then the user should be created successfully

# Other scenarios for update, delete, get user, etc.
```

### Step 3: - Write Step Definitions

Create step definition classes to map the steps in your feature files to Java code. For instance:

```java
public class UserStepDefinitions {

    @Given("I have a user with details")
    public void iHaveAUserWithDetails(DataTable dataTable) {
        // Implement logic to set up user details
    }

    @When("I create the user")
    public void iCreateTheUser() {
        // Implement logic to interact with your application (e.g., call API endpoints)
    }

    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        // Implement assertions to verify the user creation
    }

    // Define other step definitions for your scenarios
}
```

## Holding

- postgresSQL
- mapper, mapStruct

## To Add

- cucumber
- liquibase
- docker
- openapi
- messaging? (RabbitMQ, Kafka?)
- events?
- another class and create relations?