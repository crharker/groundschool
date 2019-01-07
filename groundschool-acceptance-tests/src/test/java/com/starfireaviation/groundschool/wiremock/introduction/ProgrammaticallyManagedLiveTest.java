/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.wiremock.introduction;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * ProgrammaticallyManagedLiveTest
 *
 * @author brianmichael
 */
public class ProgrammaticallyManagedLiveTest {

    /**
     * BAELDUNG_PATH
     */
    private static final String BAELDUNG_PATH = "/baeldung";

    /**
     * WireMockServer
     */
    private WireMockServer wireMockServer = new WireMockServer();

    /**
     * CloseableHttpClient
     */
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Given a programmatically managed server, when using simple stubbing, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenProgrammaticallyManagedServer_whenUsingSimpleStubbing_thenCorrect() throws IOException {
        wireMockServer.start();

        WireMock.configureFor("localhost", 8080);
        WireMock.stubFor(
                WireMock.get(WireMock.urlEqualTo(BAELDUNG_PATH)).willReturn(
                        WireMock.aResponse().withBody("Welcome to Baeldung!")));

        HttpGet request = new HttpGet("http://localhost:8080/baeldung");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertResponseToString(httpResponse);

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo(BAELDUNG_PATH)));
        Assert.assertEquals("Welcome to Baeldung!", stringResponse);

        wireMockServer.stop();
    }

    /**
     * Converts HttpResponse to a string
     *
     * @param response HttpResponse
     * @return string
     * @throws IOException when things go wrong
     */
    private static String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String stringResponse = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return stringResponse;
    }
}
