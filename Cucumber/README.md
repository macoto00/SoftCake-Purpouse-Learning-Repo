# Demo App

Simple Demo App to showcase useful stuff. 

App has one Entity `User`. We can do basic CRUD operations with the entity like:

TODO: create a specification of how to proceed every operation

- create
- get one
- get all
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
        |-- integration
          |-- AppControllerIntegrationTest.java
        |-- cucumber
          |-- features                  <-- this line --
            |-- appControllerFeature.feature
            |-- userManagementFeature.feature
          |-- stepdefinitions           <-- this line --
            |-- AppControllerStepDefinitions.java
            |-- UserManagementStepDefinitions.java
        |-- unit
          |-- controllers
            |-- AppControllerTest.java
          |-- services
            |-- UserServiceTest.java
```

Describe test scenarios using Gherkin syntax.

- Step definitions (`Scenario`, `Given`, `When`, `Then` should have unique descriptions within step definition files and across step definitions files
- The best practice to differentiate step definitions is to make the descriptions as specific as possible to reflect the behavior or action being tested in the scenario.

```gherkin
# Example feature file: appControllerFeature.feature

Feature: App Controller Functionality

  # Create user
  Scenario: Create user with valid data
    Given a user creation request with valid data
    When the create user endpoint is called
    Then the user creation should be successful

# Other scenarios for update, delete, get user, etc.
```

### Step 3: - Write Step Definitions

Create step definition classes to map the steps in your feature files to Java code. For instance:

```java
public class AppControllerStepDefinitions {
    
    // Setup

    // Scenario: Create user with valid data

    @Given("a user creation request with valid data")
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

    @Then("the user creation should be successful")
    public void theUserShouldBeCreatedSuccessfullyAppController() {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User created successfully", responseEntity.getBody());
    }

    // Define other step definitions for your scenarios
}
```

Then you can run the feature to test the behaviour.

### Potential fails

- Cucumber identifies steps based on their description, and having steps with the same description in different feature files causes conflicts.

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