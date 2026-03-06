package com.tests.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.framework.api.ApiClient;
import com.framework.utils.AssertionUtils;

import java.util.HashMap;
import java.util.Map;

public class APIStepDefs {
    private Response response;

    @Given("the User API is available")
    public void the_user_api_is_available() {
        // Base URI is already set in ApiClient
    }

    @When("I request the list of users for page {int}")
    public void i_request_the_list_of_users_for_page(Integer page) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        response = ApiClient.get("/users", params);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        AssertionUtils.assertEquals(response.getStatusCode(), statusCode, "Status code mismatch");
    }

    @Then("the response should contain user details")
    public void the_response_should_contain_user_details() {
        AssertionUtils.assertNotNull(response.jsonPath().get("data"), "User data is missing");
    }
}
