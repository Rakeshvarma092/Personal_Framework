Feature: Toucan UAM Login API Operations

  @Browser @Branch101
  Scenario: Success Login with valid credentials
    Given I have valid login credentials for Toucan
    When I send a POST request to Login API
    Then I should receive a 200 status code
    And the response success flag should be true
    And the access token should not be null

  @Browser @Branch102
  Scenario Outline: Failed Login with invalid credentials
    Given I have login credentials with identifier "<identifier>" and password "<password>"
    When I send a POST request to Login API
    Then the response success flag should be false

    Examples: 
      | identifier      | password      |
      | invalid@user    | Password@123  |
      | admin@default   | wrong_pass    |
      |                 |               |
