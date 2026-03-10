# API Integration Guide

This guide explains how to add new API automation tests to the framework.

## 1. Create POJO Models
Define the Request and Response objects as POJOs in the `com.framework.api.models` package. Use Lombok `@Data` and `@Builder` annotations for brevity.

- **Request Model**: Match the JSON payload structure.
- **Response Model**: Match the expected JSON response. Use `@JsonIgnoreProperties(ignoreUnknown = true)` to avoid errors on extra fields.

## 2. Update Configuration
Add the new API endpoint URL to the environment properties file (e.g., `src/test/resources/configs/qa.properties`).

```properties
new.api.url=https://api.example.com/v1/resource
```

## 3. Create Feature Files
Add a new `.feature` file in `src/test/resources/features`. Define scenarios for both positive and negative cases.

```gherkin
@api @new_feature
Feature: New API Functionality

  Scenario: Create a new resource
    Given I have valid data for the new resource
    When I send a POST request to the new API
    Then I should receive a 201 status code
```

## 4. Implement Step Definitions
Create a new step definition class in `src/test/java/com/tests/stepdefs`.

- Use `ApiClient` to send requests.
- Use `AssertionUtils` for validations.
- Use `ConfigReader.getProperty()` to fetch URLs.

## 5. Running Tests
Run the new tests using tags:
```bash
mvn test -Dcucumber.filter.tags="@new_feature"
```

---

## Where to Modify Summary
| Requirement | File/Package to Update |
|---|---|
| New Base URL / Endpoint | `src/test/resources/configs/*.properties` |
| New Request/Response JSON | `src/main/java/com/framework/api/models/` |
| New API Method (GET/POST/etc) | `src/main/java/com/framework/api/ApiClient.java` |
| New Test Case (BDD) | `src/test/resources/features/` |
| New Action Implementation | `src/test/java/com/tests/stepdefs/` |
