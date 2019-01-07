/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.IOException;

/**
 * GithubUserNotFoundSteps
 *
 * @author brianmichael
 */
public class GithubUserNotFoundSteps {

    /**
     * API
     */
    private String api;

    /**
     * Non-existent user
     */
    private String nonExistentUser;

    /**
     * GitHub response code
     */
    private int githubResponseCode;

    /**
     * Given github user profile api
     */
    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    /**
     * Given a random non-existent username
     */
    @Given("a random non-existent username")
    public void givenANonexistentUsername() {
        nonExistentUser = RandomStringUtils.randomAlphabetic(8);
    }

    /**
     * When I look for the random user via the api
     *
     * @throws IOException when things go wrong
     */
    @When("I look for the random user via the api")
    public void whenILookForTheUserViaTheApi() throws IOException {
        githubResponseCode = getGithubUserProfile(api, nonExistentUser)
                .getStatusLine()
                .getStatusCode();
    }

    /**
     * When I look for $user via the api
     *
     * @param user user
     * @throws IOException when things go wrong
     */
    @When("I look for $user via the api")
    public void whenILookForSomeNonExistentUserViaTheApi(String user) throws IOException {
        githubResponseCode = getGithubUserProfile(api, user)
                .getStatusLine()
                .getStatusCode();
    }

    /**
     * Gets GitHub user profile
     *
     * @param api api
     * @param username username
     * @return HttpResponse
     * @throws IOException when things go wrong
     */
    static HttpResponse getGithubUserProfile(String api, String username) throws IOException {
        HttpUriRequest request = new HttpGet(String.format(api, username));
        return HttpClientBuilder
                .create()
                .build()
                .execute(request);
    }

    /**
     * Then github respond: 404 not found
     */
    @Then("github respond: 404 not found")
    public void thenGithubRespond404NotFound() {
        Assert.assertTrue(HttpStatus.SC_NOT_FOUND == githubResponseCode);
    }
}
