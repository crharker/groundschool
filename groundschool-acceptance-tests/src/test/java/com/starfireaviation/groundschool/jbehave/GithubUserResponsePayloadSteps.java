/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

import org.apache.http.HttpResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.starfireaviation.groundschool.GitHubUser;
import com.starfireaviation.groundschool.RetrieveUtil;

import java.io.IOException;

/**
 * GithubUserResponsePayloadSteps
 *
 * @author brianmichael
 */
public class GithubUserResponsePayloadSteps {

    /**
     * API
     */
    private String api;

    /**
     * GitHubUser
     */
    private GitHubUser resource;

    /**
     * Given github user profile api
     */
    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    /**
     * When I look for $user via the api
     *
     * @param user user
     * @throws IOException when things go wrong
     */
    @When("I look for $user via the api")
    public void whenILookForEugenpViaTheApi(String user) throws IOException {
        HttpResponse httpResponse = GithubUserNotFoundSteps.getGithubUserProfile(api, user);
        resource = RetrieveUtil.retrieveResourceFromResponse(httpResponse, GitHubUser.class);
    }

    /**
     * Then github's response contains a 'login' payload same as $username
     *
     * @param username username
     */
    @Then("github's response contains a 'login' payload same as $username")
    public void thenGithubsResponseContainsAloginPayloadSameAsEugenp(String username) {
        MatcherAssert.assertThat(username, Matchers.is(resource.getLogin()));
    }

}
