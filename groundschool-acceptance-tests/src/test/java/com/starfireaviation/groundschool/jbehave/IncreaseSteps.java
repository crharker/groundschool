/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.Random;

/**
 * IncreaseSteps
 *
 * @author brianmichael
 */
public class IncreaseSteps {
    /**
     * Counter
     */
    private int counter;

    /**
     * Previous value
     */
    private int previousValue;

    /**
     * Given a counter
     */
    @Given("a counter")
    public void aCounter() {
        // A counter
    }

    /**
     * Given the counter has any integral value
     */
    @Given("the counter has any integral value")
    public void counterHasAnyIntegralValue() {
        counter = new Random().nextInt();
        previousValue = counter;
    }

    /**
     * When the user increases the counter
     */
    @When("the user increases the counter")
    public void increasesTheCounter() {
        counter++;
    }

    /**
     * Then the value of the counter must be 1 greater than previous value
     */
    @Then("the value of the counter must be 1 greater than previous value")
    public void theValueOfTheCounterMustBe1Greater() {
        Assert.assertTrue(1 == counter - previousValue);
    }
}
