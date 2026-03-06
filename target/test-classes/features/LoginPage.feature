#@ui
#Feature: Google Search Functionality
#
#  @Browser @Branch0
#  Scenario: Search for Antigravity AI
#    Given I navigate to Google
#    When I search for "Antigravity AI"
#    Then I should see search results related to "Antigravity AI"

Feature: Login Functionality

  # ==================== LOGIN PAGE VERIFICATION ====================

  @Browser @Branch0
  Scenario: Verify Login page is displayed correctly

    Given user navigates to the application URL
    Then Verify "Login" page is visible

  # ==================== VALID LOGIN SCENARIOS ====================

#  @Browser @Branch1
#  Scenario: Verify successful login with valid credentials
#
#    Given user navigates to the application URL
#    Then Verify "Login" page is visible
#    When user enters Email ID as "admin@default"
#    And user enters Password as "Password@123"
#    And user clicks on Login button
#    Then user should be navigated to the dashboard
#    And user should see the header name as "SWITCH OVERVIEW"
