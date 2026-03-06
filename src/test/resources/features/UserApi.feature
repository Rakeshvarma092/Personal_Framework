@api
Feature: User API CRUD Operations

  Scenario: Get list of users
    Given the User API is available
    When I request the list of users for page 2
    Then the response status code should be 200
    And the response should contain user details
