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
 * QuestionSteps
 *
 * @author brianmichael
 */
public class QuestionSteps extends BaseSteps {

    /**
     * Given a question exists
     *
     * @throws IOException when things go wrong
     */
    @Given("^a question exists$")
    public void aQuestionExists() throws IOException {
        // Do something
    }

    /**
     * Given a question does not exist
     *
     * @throws IOException when things go wrong
     */
    @Given("^a question does not exist$")
    public void anQuestionDoesNotExists() throws IOException {
        // Do something
    }

    /**
     * When I list all questions
     *
     * @throws IOException when things go wrong
     */
    @When("^I list all questions$")
    public void iListAllQuestions() throws IOException {
        // Do something
    }

    /**
     * When I retrieve a question
     *
     * @throws IOException when things go wrong
     */
    @When("^I retrieve a question$")
    public void iRetrieveAQuestion() throws IOException {
        // Do something
    }

    /**
     * When I update a question
     *
     * @throws IOException when things go wrong
     */
    @When("^I update a question$")
    public void iUpdateAQuestion() throws IOException {
        // Do something
    }

    /**
     * When I delete a question
     *
     * @throws IOException when things go wrong
     */
    @When("^I delete a question$")
    public void iDeleteAQuestion() throws IOException {
        // Do something
    }

    /**
     * Then I should receive a list of questions
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive a list of questions$")
    public void receiveAListOfQuestions() throws IOException {
        // Do something
    }

    /**
     * Then I should receive the question
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive the question$")
    public void receiveTheQuestion() throws IOException {
        // Do something
    }

    /**
     * Then The question should be updated
     *
     * @throws IOException when things go wrong
     */
    @Then("^The question should be updated$")
    public void theQuestionShouldBeUpdated() throws IOException {
        // Do something
    }

    /**
     * Then I should receive a question not found error
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive a question not found error$")
    public void iReceiveAQuestionNotFoundError() throws IOException {
        // Do something
    }

    /**
     * Then The question should be deleted
     *
     * @throws IOException when things go wrong
     */
    @Then("^The question should be deleted$")
    public void theQuestionShouldBeDeleted() throws IOException {
        // Do something
    }

}
