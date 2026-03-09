package com.tests.stepdefs;

import api.models.LoginRequest;
import api.models.LoginResponse;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import api.ApiClient;
import utils.AssertionUtils;

public class APIStepDefs {
    private LoginRequest loginRequest;
    private Response response;
    private LoginResponse loginResponse;

    //==================================== Login API Steps ====================================//
    @Given("I have valid login credentials for Toucan")
    public void i_have_valid_login_credentials_for_toucan() {
        loginRequest = LoginRequest.builder()
                .messageID("/api/uam/auth/login")
                .requestType("")
                .requestObject(LoginRequest.RequestObject.builder()
                        .identifier("admin@default")
                        .password("Password@123")
                        .applicationUrl("https://admin-dev.swt.toucanint.com")
                        .build())
                .build();
    }

    @Given("I have login credentials with identifier {string} and password {string}")
    public void i_have_login_credentials_with_identifier_and_password(String identifier, String password) {
        loginRequest = LoginRequest.builder()
                .messageID("/api/uam/auth/login")
                .requestType("")
                .requestObject(LoginRequest.RequestObject.builder()
                        .identifier(identifier)
                        .password(password)
                        .applicationUrl("https://admin-dev.swt.toucanint.com")
                        .build())
                .build();
    }
    @When("I send a POST request to Login API")
    public void i_send_a_post_request_to_login_api() {
        String loginUrl = ConfigReader.getProperty("login.api.url");
        // Using common post for absolute URL or splitting it
        response = ApiClient.post(loginUrl, "", loginRequest);
    }

    @Then("the response success flag should be true")
    public void the_response_success_flag_should_be_true() {
        loginResponse = response.as(LoginResponse.class);
        AssertionUtils.assertTrue(loginResponse.isSuccess(), "Success flag is false");
    }

    @Then("the response success flag should be false")
    public void the_response_success_flag_should_be_false() {
        loginResponse = response.as(LoginResponse.class);
        AssertionUtils.assertTrue(!loginResponse.isSuccess(), "Success flag is true but expected false");
    }
    @Then("the access token should not be null")
    public void the_access_token_should_not_be_null() {
        AssertionUtils.assertNotNull(loginResponse.getAccess_token(), "Access token is null");
    }
    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int arg0) {
    }
}
