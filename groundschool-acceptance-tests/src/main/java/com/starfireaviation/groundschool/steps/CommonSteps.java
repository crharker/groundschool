/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

/**
 * CommonSteps
 *
 * @author brianmichael
 */
public class CommonSteps extends BaseSteps {

    /**
     * Before each scenario...
     *
     * @param scenario Scenario
     */
    @Before
    public void before(Scenario scenario) {
        // Before
    }

    /**
     * Then I should receive a operation not permitted error
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive a access denied error$")
    public void iShouldReceiveAAccessDeniedError() throws Exception {
        // Do something
        // TODO throw new PendingException();
    }

}
