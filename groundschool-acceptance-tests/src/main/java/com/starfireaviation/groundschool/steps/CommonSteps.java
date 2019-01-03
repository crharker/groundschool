/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * Common test steps
 *
 * @author bm250290
 */
public class CommonSteps extends Steps {

    /**
     * Setup tests
     */
    @Before
    public void setup() {
        // setup
    }

    /**
     * I am a %s user
     *
     * @return Role
     */
    @Given("^I am a admin user$")
    public String iAmAUser() {
        return "";
    }

}
