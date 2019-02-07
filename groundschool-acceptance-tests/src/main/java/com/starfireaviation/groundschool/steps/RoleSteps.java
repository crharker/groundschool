/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import com.starfireaviation.groundschool.model.Role;

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
     * @param userRole user role
     * @throws Exception when things go wrong
     */
    @Given("^I am a (.+) user$")
    public void iAmAUser(String userRole) throws Exception {
        role = Role.valueOf(userRole.toUpperCase());
    }

}
