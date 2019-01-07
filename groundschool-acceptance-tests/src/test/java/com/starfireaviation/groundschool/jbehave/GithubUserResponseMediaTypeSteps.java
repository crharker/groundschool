/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

import org.apache.http.entity.ContentType;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.IOException;

/**
 * GithubUserResponseMediaTypeSteps
 *
 * @author brianmichael
 */
public class GithubUserResponseMediaTypeSteps {

    /**
     * API
     */
    private String api;

    /**
     * Valid user
     */
    private String validUser;

    /**
     * Media type
     */
    private String mediaType;

    /**
     * Given github user profile api
     */
    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    /**
     * Given a valid username
     */
    @Given("a valid username")
    public void givenAValidUsername() {
        validUser = "eugenp";
    }

    /**
     * When I look for the user via the api
     *
     * @throws IOException when things go wrong
     */
    @When("I look for the user via the api")
    public void whenILookForTheUserViaTheApi() throws IOException {
        mediaType = ContentType
                .getOrDefault(GithubUserNotFoundSteps.getGithubUserProfile(api, validUser).getEntity())
                .getMimeType();
    }

    /**
     * Then github respond data of type json
     */
    @Then("github respond data of type json")
    public void thenGithubRespondDataOfTypeJson() {
        Assert.assertEquals("application/json", mediaType);
    }
}
