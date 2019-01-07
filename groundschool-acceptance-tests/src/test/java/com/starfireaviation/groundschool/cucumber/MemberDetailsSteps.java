/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.cucumber;

import java.io.IOException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * UserSteps
 *
 * @author brianmichael
 */
public class MemberDetailsSteps extends BaseSteps {

    /**
     * Given a member exists
     *
     * @throws IOException when things go wrong
     */
    @Given("^a member exists$")
    public void aMemberExists() throws IOException {
        // Do something
    }

    /**
     * When I list all members
     *
     * @throws IOException when things go wrong
     */
    @When("^I list all members$")
    public void iListAllMembers() throws IOException {
        // Do something
    }

    /**
     * Then I should receive a list of members
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive a list of members$")
    public void receiveAListOfMembers() throws IOException {
        // Do something
    }

}
