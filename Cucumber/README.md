# Demo App

TODO: 

1) Insert 3 users into DB by starting the App not working

Simple Demo App to showcase useful stuff.

App has one Entity `User`. We can do basic CRUD operations with the entity like:

- create (POST request `/api/v1/users`)

```JSON
{
    "firstName": "Mark",
    "lastName": "Doe",
    "email": "mark@example.com"
}
```

- get one (GET request `/api/v1/users/{id}`)

- get all (GET request `/api/v1/users`)

- update (PUT request `/api/v1/users/{id}`)

```JSON
{
  "firstName": "Eva",
  "lastName": "Doe",
  "email": "eva@example.com"
}
```

- delete (DELETE request `/api/v1/users/{id}`)

By starting the App, there will be 3 users created into the DB via Liquibase.

```JSON
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com"
  },
  {
    "id": 3,
    "firstName": "Alice",
    "lastName": "Johnson",
    "email": "alice@example.com"
  }
]
```

## PostgresSQL

App is connected to PostgresSQL database. 

## Mapstruct

App is using mapping of objects.

- You can find the relevant part in `mappers` folder.

## Liquibase

- Liquibase is an open-source tool for database schema version control and management. 
- It helps developers track, manage, and apply changes to database schemas in a consistent and automated way across different environments. 
- Essentially, it allows for easier database migrations, ensuring that changes are tracked, reversible, and applied reliably.

### Start

- Include dependencies along with the necessary plugins.
- Follow the structure for scripts:

```bash
|-- application.properties
|-- static
|-- templates
|-- db
  |-- changelog.yml            <-- main file
  |-- development
    |-- development.main.yml   <-- main development file
    |-- inserts
      |-- 01-users.xml
  |-- releases
    |-- releases.main.yml      <-- main release file
    |-- 2024.0
      |-- 01-users.xml
```

You can find more in corresponding files.

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

Create `.feature` files in the `src/test/features` directory and corresponding step definition classes in the `src/test/stepdefinitions` directory within the test source directory.

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

- Step definitions (`Scenario`, `Given`, `When`, `Then` should have unique descriptions within step definition files and across step definitions files.
- The best practice to differentiate step definitions is to make the descriptions as specific as possible to reflect the behavior or action being tested in the scenario.

```gherkin
# Example feature file: appControllerFeature.feature

Feature: App Controller Functionality

  # Create user
  Scenario: Creating a user with valid data in App Controller
    Given a valid user creation request is sent to the App Controller
    When the create user endpoint is called
    Then the user creation should be successful in App Controller

# Other scenarios for update, delete, get user, etc.
```

### Step 3: - Write Step Definitions

Create step definition classes to map the steps in your feature files to Java code. For instance:

```java
public class AppControllerStepDefinitions {
    
    // Setup

    // Scenario: Create user with valid data

    @Given("a valid user creation request is sent to the App Controller")
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

    @Then("the user creation should be successful in App Controller")
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

## Other changes

- Replaced `application.properties` with `application.yml`.

## App is using

- postgresSQL
- mapper, mapStruct
- unit tests
- integration tests
- cucumber tests
- liquibase

## To Add

- docker
- openapi
- messaging? (RabbitMQ, Kafka?)
- events?
- another class and create relations?