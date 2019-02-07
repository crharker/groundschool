/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import org.junit.Assert;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.util.ObjectCreator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * UserSteps
 *
 * @author brianmichael
 */
public class UserSteps extends BaseSteps {

    /**
     * Given a user exists
     *
     * @throws Exception when things go wrong
     */
    @Given("^a user exists$")
    public void aUserExists() throws Exception {
        user = ObjectCreator.getUser(Role.STUDENT);
    }

    /**
     * Given I want to signup
     *
     * @throws Exception when things go wrong
     */
    @Given("^I want to signup$")
    public void iWantToSignup() throws Exception {
        final User prospect = ObjectCreator.getUser(role);
        prospect.setId(null);
        user = prospect;
    }

    /**
     * Given a user does not exist
     *
     * @throws Exception when things go wrong
     */
    @Given("^a user does not exist$")
    public void aUserDoesNotExists() throws Exception {
        user = null;
    }

    /**
     * When I list all users
     *
     * @throws Exception when things go wrong
     */
    @When("^I list all users$")
    public void iListAllUsers() throws Exception {
        users = request
                .when()
                .headers(getHeaders())
                .get(APPLICATION_HOST + "/users")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", User.class);
    }

    /**
     * When I retrieve a user
     *
     * @throws Exception when things go wrong
     */
    @When("^I retrieve a user$")
    public void iRetrieveAUser() throws Exception {
        user = request
                .when()
                .headers(getHeaders())
                .get(APPLICATION_HOST + "/users/" + user.getId())
                .then()
                .extract()
                .as(User.class);
    }

    /**
     * When I retrieve my details
     *
     * @throws Exception when things go wrong
     */
    @When("^I retrieve my details$")
    public void iRetrieveMyDetails() throws Exception {
        // Do something
    }

    /**
     * When I update a user
     *
     * @throws Exception when things go wrong
     */
    @When("^I update a user$")
    public void iUpdateAUser() throws Exception {
        // Do something
    }

    /**
     * When I delete a user
     *
     * @throws Exception when things go wrong
     */
    @When("^I delete a user$")
    public void iDeleteAUser() throws Exception {
        request
                .when()
                .headers(getHeaders())
                .delete(APPLICATION_HOST + "/users");
        // Do something
    }

    /**
     * When I signup
     *
     * @throws Exception when things go wrong
     */
    @When("^I signup$")
    public void iSignup() throws Exception {
        user = request
                .when()
                .headers(getHeaders())
                .post(APPLICATION_HOST + "/users", user)
                .then()
                .extract()
                .as(User.class);
    }

    /**
     * When I login
     *
     * @throws Exception when things go wrong
     */
    @When("^I login$")
    public void iLogin() throws Exception {
        request
                .when()
                .headers(getHeaders())
                .post(APPLICATION_HOST + "/users/" + user.getId() + "/login");
    }

    /**
     * When I verify my <setting> setting
     *
     * @param setting user setting
     * @throws Exception when things go wrong
     */
    @When("^I verify my (.+) setting$")
    public void iVerifySetting(String setting) throws Exception {
        request
                .when()
                .get(APPLICATION_HOST + "/users/" + user.getId() + "/verify/" + setting.toUpperCase());
    }

    /**
     * Then I should receive a list of users
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive a list of users$")
    public void receiveAListOfUsers() throws Exception {
        // Do something
    }

    /**
     * Then I should receive the user
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive the user details$")
    public void receiveTheUser() throws Exception {
        Assert.assertNotNull("User is null", user);
        Assert.assertNotNull("User ID is null", user.getId());
        Assert.assertEquals(
                String.format("First name values, expected=[%s] actual=[%s], do not match"),
                ObjectCreator.FIRST_NAME,
                user.getFirstName());
        Assert.assertEquals(
                String.format("Last name values, expected=[%s] actual=[%s], do not match"),
                ObjectCreator.LAST_NAME,
                user.getLastName());
    }

    /**
     * Then The user should be updated
     *
     * @throws Exception when things go wrong
     */
    @Then("^The user should be updated$")
    public void theUserShouldBeUpdated() throws Exception {
        // Do something
    }

    /**
     * Then I should receive a user not found error
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive a user not found error$")
    public void iReceiveAUserNotFoundError() throws Exception {
        // Do something
    }

    /**
     * Then The user should be deleted
     *
     * @throws Exception when things go wrong
     */
    @Then("^The user should be deleted$")
    public void theUserShouldBeDeleted() throws Exception {
        // Do something
    }

    /**
     * Then I should receive an authentication token
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive an authentication token$")
    public void iShouldReceiveAnAuthenticationToken() throws Exception {
        // Do something
    }

    /**
     * Then My <setting> should be verified
     *
     * @param setting user setting
     * @throws Exception when things go wrong
     */
    @Then("^My (.+) should be verified$")
    public void mySettingShouldBeVerified(String setting) throws Exception {
        // Do something
    }

}
