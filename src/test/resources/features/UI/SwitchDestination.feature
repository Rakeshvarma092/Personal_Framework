Feature: Switch Destination Configuration

  Background: User is logged in and on the dashboard
    Given user navigates to the application URL
    And user enters Email ID as "admin@default"
    And user enters Password as "Password@123"
    And user clicks on Login button
    Then user should be navigated to the dashboard

  @Browser @Branch3
  Scenario: Create a new Switch Destination configuration
    When user navigates to the Switch Destination page
    Then the header "Switch Configuration" should be displayed
    When user clicks on Create button
    Then the title "New Switch Configuration" should be displayed
    When user selects Scheme as "VISA"
    And user enters configuration details:
      | Name        | Acquirer ID | Forward ID | Facilitator ID | Group SignOn |
      | TestDest_01 | 123456      | 654321     | FACIL_01       | GRP_01       |
    And user clicks on Next button
    And user clicks on Add button
    And user enters transaction details:
      | Transaction Type | Transaction Channel | Processing Code | MTI  | Transaction Expiry |
      | AUTH             | PG                  | 00              | 0100 | 30                 |
    And user clicks on Save button
    Then the configuration "TestDest_01" should be successfully created

  @Browser @Branch4
  Scenario: Verify validation on Switch Destination page
    When user navigates to the Switch Destination page
    And user clicks on Create button
    And user clicks on Save button without entering details
    Then user should see mandatory field errors
