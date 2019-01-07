/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.karate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * KarateIntegrationTest
 *
 * @author brianmichael
 */
@RunWith(Karate.class)
@CucumberOptions(features = "classpath:karate")
public class KarateIntegrationTest {

    /**
     * WireMockServer
     */
    private static final WireMockServer wireMockServer = new WireMockServer();

    /**
     * Set up
     *
     * @throws Exception when things go wrong
     */
    @BeforeClass
    public static void setUp() throws Exception {
        wireMockServer.start();

        WireMock.configureFor("localhost", 8080);
        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlEqualTo("/user/get"))
                        .willReturn(
                                WireMock
                                        .aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{ \"id\": \"1234\", name: \"John Smith\" }")));
        WireMock.stubFor(
                WireMock
                        .post(WireMock.urlEqualTo("/user/create"))
                        .withHeader("content-type", WireMock.equalTo("application/json"))
                        .withRequestBody(WireMock.containing("id"))
                        .willReturn(
                                WireMock
                                        .aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{ \"id\": \"1234\", name: \"John Smith\" }")));

    }

    /**
     * Tear down
     *
     * @throws Exception when things go wrong
     */
    @AfterClass
    public static void tearDown() throws Exception {
        wireMockServer.stop();
    }

}
