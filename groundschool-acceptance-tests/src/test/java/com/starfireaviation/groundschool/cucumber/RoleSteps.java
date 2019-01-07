/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.cucumber;

import java.io.IOException;
import cucumber.api.java.en.Given;

/**
 * RoleSteps
 *
 * @author brianmichael
 */
public class RoleSteps {

    /**
     * Given I am a (.+) user
     *
     * @param role user role
     * @throws IOException when things go wrong
     */
    @Given("^I am a (.+) user$")
    public void iAmAUser(String role) throws IOException {
        // Do something
    }

}
