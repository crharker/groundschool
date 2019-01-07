/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.cucumber;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * StepDefinition
 *
 * @author brianmichael
 */
public class StepDefinition {

    /**
     * CREATE_PATH
     */
    private static final String CREATE_PATH = "/create";

    /**
     * APPLICATION_JSON
     */
    private static final String APPLICATION_JSON = "application/json";

    /**
     * Cucumber.json input stream
     */
    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");

    /**
     * JSON string
     */
    private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

    /**
     * WireMockServer
     */
    private final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());

    /**
     * CloseableHttpClient
     */
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * When users upload data on a project
     *
     * @throws IOException when things go wrong
     */
    @When("^users upload data on a project$")
    public void usersUploadDataOnAProject() throws IOException {
        wireMockServer.start();

        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(
                WireMock
                        .post(WireMock.urlEqualTo(CREATE_PATH))
                        .withHeader("content-type", WireMock.equalTo(APPLICATION_JSON))
                        .withRequestBody(WireMock.containing("testing-framework"))
                        .willReturn(WireMock.aResponse().withStatus(200)));

        HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/create");
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        WireMock.verify(
                WireMock
                        .postRequestedFor(WireMock.urlEqualTo(CREATE_PATH))
                        .withHeader("content-type", WireMock.equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    /**
     * When users want to get information on the (.+) project
     *
     * @param projectName project name
     * @throws IOException when things go wrong
     */
    @When("^users want to get information on the (.+) project$")
    public void usersGetInformationOnAProject(String projectName) throws IOException {
        wireMockServer.start();

        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlEqualTo("/projects/cucumber"))
                        .withHeader("accept", WireMock.equalTo(APPLICATION_JSON))
                        .willReturn(WireMock.aResponse().withBody(jsonString)));

        HttpGet request = new HttpGet(
                "http://localhost:" + wireMockServer.port() + "/projects/" + projectName.toLowerCase());
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = convertResponseToString(httpResponse);

        Assert.assertThat(responseString, CoreMatchers.containsString("\"testing-framework\": \"cucumber\""));
        Assert.assertThat(responseString, CoreMatchers.containsString("\"website\": \"cucumber.io\""));
        WireMock.verify(
                WireMock.getRequestedFor(WireMock.urlEqualTo("/projects/cucumber")).withHeader(
                        "accept",
                        WireMock.equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    /**
     * Then the server should handle it and return a success status
     */
    @Then("^the server should handle it and return a success status$")
    public void theServerShouldReturnASuccessStatus() {
        // Do nothing
    }

    /**
     * Then the requested data is returned
     */
    @Then("^the requested data is returned$")
    public void theRequestedDataIsReturned() {
        // Do nothing
    }

    /**
     * Convert HTTP response to a string
     *
     * @param response HttpResponse
     * @return String
     * @throws IOException when things go wrong
     */
    private static String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
}
