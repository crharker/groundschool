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
 * QuizSteps
 *
 * @author brianmichael
 */
public class QuizSteps extends BaseSteps {

    /**
     * Given a quiz exists
     *
     * @throws IOException when things go wrong
     */
    @Given("^a quiz exists$")
    public void aQuizExists() throws IOException {
        // Do something
    }

    /**
     * Given a quiz does not exist
     *
     * @throws IOException when things go wrong
     */
    @Given("^a quiz does not exist$")
    public void anQuizDoesNotExists() throws IOException {
        // Do something
    }

    /**
     * When I list all quizzes
     *
     * @throws IOException when things go wrong
     */
    @When("^I list all quizzes$")
    public void iListAllQuizzes() throws IOException {
        // Do something
    }

    /**
     * When I retrieve a quiz
     *
     * @throws IOException when things go wrong
     */
    @When("^I retrieve a quiz$")
    public void iRetrieveAQuiz() throws IOException {
        // Do something
    }

    /**
     * When I update a quiz
     *
     * @throws IOException when things go wrong
     */
    @When("^I update a quiz$")
    public void iUpdateAQuiz() throws IOException {
        // Do something
    }

    /**
     * When I delete a quiz
     *
     * @throws IOException when things go wrong
     */
    @When("^I delete a quiz$")
    public void iDeleteAQuiz() throws IOException {
        // Do something
    }

    /**
     * Then I should receive a list of quizzes
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive a list of quizzes$")
    public void receiveAListOfQuizzes() throws IOException {
        // Do something
    }

    /**
     * Then I should receive the quiz
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive the quiz$")
    public void receiveTheQuiz() throws IOException {
        // Do something
    }

    /**
     * Then The quiz should be updated
     *
     * @throws IOException when things go wrong
     */
    @Then("^The quiz should be updated$")
    public void theQuizShouldBeUpdated() throws IOException {
        // Do something
    }

    /**
     * Then I should receive a quiz not found error
     *
     * @throws IOException when things go wrong
     */
    @Then("^I should receive a quiz not found error$")
    public void iReceiveAQuizNotFoundError() throws IOException {
        // Do something
    }

    /**
     * Then The quiz should be deleted
     *
     * @throws IOException when things go wrong
     */
    @Then("^The quiz should be deleted$")
    public void theQuizShouldBeDeleted() throws IOException {
        // Do something
    }

}
