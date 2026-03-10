Feature: Update Scheme Destination API

  Background: User is authenticated
    # Ideally we'd have a step to login and get token, but here we assume a token is available in config or scenario context

  @Browser @Branch103
  Scenario: Update scheme destination successfully
    Given I have a valid update scheme destination payload
    When I send a POST request to Update Scheme Destination API with a valid token
    Then the API response status code should be 200
    And the response success flag should be true
    And the response message should be "Updated Successfully "
    And the responseObject id should be "6870f514b889d3727c6cd6c1"
    And the responseObject name should be "RUPAY"
    And the acqBin in networkControls should be "652154"

  @Browser @Branch104
  Scenario: Update scheme destination with missing ID
    Given I have an update scheme destination payload with missing "id"
    When I send a POST request to Update Scheme Destination API with a valid token
    Then the API response status code should be 400
    And the response success flag should be false

  @Browser @Branch105
  Scenario: Update scheme destination with unauthorized token
    Given I have a valid update scheme destination payload
    When I send a POST request to Update Scheme Destination API with an invalid token
    Then the API response status code should be 401
