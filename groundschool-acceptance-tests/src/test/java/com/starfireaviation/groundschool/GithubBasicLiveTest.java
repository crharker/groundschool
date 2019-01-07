/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

/**
 *
 * @author brianmichael
 */
public class GithubBasicLiveTest {

    // simple request - response

    /**
     * Given user does not exist, when user info is retrieved, then 404 is received
     *
     * @throws ClientProtocolException when things go wrong
     * @throws IOException when things go wrong
     */
    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived() throws ClientProtocolException,
            IOException {
        // Given
        final String name = RandomStringUtils.randomAlphabetic(8);
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);

        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertThat(httpResponse.getStatusLine().getStatusCode(), Matchers.equalTo(HttpStatus.SC_NOT_FOUND));
    }

    /**
     * Given request with no accept header, when request is executed, then default response type is JSON
     *
     * @throws ClientProtocolException when things go wrong
     * @throws IOException when things go wrong
     */
    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
            throws ClientProtocolException, IOException {
        // Given
        final String jsonMimeType = "application/json";
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/eugenp");

        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assert.assertEquals(jsonMimeType, mimeType);
    }

    /**
     * Given user exists, when user info is retrieved, then retrieved resource is correct
     *
     * @throws ClientProtocolException when things go wrong
     * @throws IOException when things go wrong
     */
    @Test
    public void givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
            throws ClientProtocolException, IOException {
        // Given
        final HttpUriRequest request = new HttpGet("https://api.github.com/users/eugenp");

        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        final GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(response, GitHubUser.class);
        Assert.assertThat("eugenp", Matchers.is(resource.getLogin()));
    }

}
