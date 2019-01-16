/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import cucumber.api.java.en.Given;

/**
 * RoleSteps
 *
 * @author brianmichael
 */
public class RoleSteps extends BaseSteps {

    /**
     * Given I am a (.+) user
     *
     * @param role user role
     * @throws Exception when things go wrong
     */
    @Given("^I am a (.+) user$")
    public void iAmAUser(String role) throws Exception {
        // Do something
    }

}
