package com.tests.stepdefs;

import api.models.LoginRequest;
import api.models.LoginResponse;
import api.models.UpdateSchemeRequest;
import api.models.UpdateSchemeResponse;
import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import api.ApiClient;
import utils.AssertionUtils;

import java.util.ArrayList;
import java.util.List;

public class APIStepDefs {
    private LoginRequest loginRequest;
    private Response response;
    private LoginResponse loginResponse;
    private UpdateSchemeRequest updateSchemeRequest;
    private UpdateSchemeResponse updateSchemeResponse;
    private String token = "dummy_token"; // In real scenario, fetch this from Login API or ScenarioContext

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
        if (response.getStatusCode() == 200) {
            loginResponse = response.as(LoginResponse.class);
        }
    }

    @Then("the response success flag should be true")
    public void the_response_success_flag_should_be_true() {
        AssertionUtils.assertTrue(response.jsonPath().getBoolean("success"), "Success flag is false");
    }

    @Then("the response success flag should be false")
    public void the_response_success_flag_should_be_false() {
        AssertionUtils.assertTrue(!response.jsonPath().getBoolean("success"), "Success flag is true but expected false");
    }
    @Then("the access token should not be null")
    public void the_access_token_should_not_be_null() {
        AssertionUtils.assertNotNull(loginResponse.getAccess_token(), "Access token is null");
    }
    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int arg0) {
    }
    //==================================== Update Scheme Destination Steps ====================================//
    @Given("I have a valid update scheme destination payload")
    public void iHaveAValidUpdateSchemeDestinationPayload() {
        List<UpdateSchemeRequest.SchemeMessageType> messages = new ArrayList<>();
        String[] types = {"AUTH", "SAVE_CARD", "AUTH_SAVE_CARD", "REVERSAL", "AUTO_PAY", "PREAUTH"};
        for (String type : types) {
            messages.add(UpdateSchemeRequest.SchemeMessageType.builder()
                    .txnAllow("ONLINE")
                    .isSingle(false)
                    .authMessageTypeEnum(type)
                    .mti(type.equals("REVERSAL") ? "0400" : "0100")
                    .processingCode("00")
                    .txnSource("PG")
                    .isTxnAllowed(true)
                    .txnExpiry(15)
                    .build());
        }

        UpdateSchemeRequest.NetworkControls network = UpdateSchemeRequest.NetworkControls.builder()
                .participantId("608552")
                .acqBin("652154")
                .currency("INR")
                .processingId("12345678901")
                .fileCategory("Test")
                .acquirerReferanceData("12345678")
                .originatingMessageFormat("RECEIVED_MESSAGE_IN_THE_IPM_FORMAT")
                .acquirerDestinationId("123333")
                .build();

        UpdateSchemeRequest.RequestObject obj = UpdateSchemeRequest.RequestObject.builder()
                .id("6870f514b889d3727c6cd6c1")
                .switchDestination("RUPAY")
                .name("RUPAY")
                .acquirerId("034605")
                .forwardingId("014620")
                .paymentFacilitatorId("123456")
                .groupSignOn("60855")
                .isEchoAllowed(true)
                .schemeMessageType(messages)
                .networkControls(network)
                .build();

        updateSchemeRequest = UpdateSchemeRequest.builder().requestObject(obj).build();
    }

    @Given("I have an update scheme destination payload with missing {string}")
    public void iHaveAnUpdateSchemeDestinationPayloadWithMissing(String field) {
        iHaveAValidUpdateSchemeDestinationPayload();
        if (field.equalsIgnoreCase("id")) {
            updateSchemeRequest.getRequestObject().setId(null);
        }
    }

    @When("I send a POST request to Update Scheme Destination API with a valid token")
    public void iSendAPOSTRequestToUpdateSchemeDestinationAPIWithAValidToken() {
        String url = ConfigReader.getProperty("update.scheme.api.url");
        response = ApiClient.post(url, "", updateSchemeRequest, token);
        if (response.getStatusCode() == 200) {
            updateSchemeResponse = response.as(UpdateSchemeResponse.class);
        }
    }

    @When("I send a POST request to Update Scheme Destination API with an invalid token")
    public void iSendAPOSTRequestToUpdateSchemeDestinationAPIWithAnInvalidToken() {
        String url = ConfigReader.getProperty("update.scheme.api.url");
        response = ApiClient.post(url, "", updateSchemeRequest, "invalid_token_123");
    }

    @Then("the API response status code should be {int}")
    public void theAPIResponseStatusCodeShouldBe(int statusCode) {
        AssertionUtils.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch");
    }


    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        AssertionUtils.assertEquals(updateSchemeResponse.getMessage(), expectedMessage, "Success message mismatch");
    }

    @Then("the responseObject id should be {string}")
    public void theResponseObjectIdShouldBe(String expectedId) {
        AssertionUtils.assertEquals(updateSchemeResponse.getResponseObject().getId(), expectedId, "ID mismatch");
    }

    @Then("the responseObject name should be {string}")
    public void theResponseObjectNameShouldBe(String expectedName) {
        AssertionUtils.assertEquals(updateSchemeResponse.getResponseObject().getName(), expectedName, "Name mismatch");
    }

    @Then("the acqBin in networkControls should be {string}")
    public void theAcqBinInNetworkControlsShouldBe(String expectedBin) {
        AssertionUtils.assertEquals(updateSchemeResponse.getResponseObject().getNetworkControls().getAcqBin(), expectedBin, "AcqBin mismatch");
    }
}