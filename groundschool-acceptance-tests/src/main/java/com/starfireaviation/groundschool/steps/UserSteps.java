/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import cucumber.api.java.en.When;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.controller.UserController;

/**
 * User endpoint tests
 *
 * @author brianmichael
 */
public class UserSteps extends Steps {

    /**
     * UsersService
     */
    @Autowired
    UserController usersService;

    /**
     * When I list all users
     *
     * @return response
     * @throws Throwable when an error occurs
     */
    @When("^I list all users$")
    public List<User> iListAllUsers() throws Throwable {
        return usersService.list();
    }

}
