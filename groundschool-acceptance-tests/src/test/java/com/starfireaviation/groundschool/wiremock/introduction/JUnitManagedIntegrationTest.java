/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.wiremock.introduction;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 *
 * @author brianmichael
 */
public class JUnitManagedIntegrationTest {

    /**
     * BAELDUNG_WIREMOCK_PATH
     */
    private static final String BAELDUNG_WIREMOCK_PATH = "/baeldung/wiremock";

    /**
     * APPLICATION_JSON
     */
    private static final String APPLICATION_JSON = "application/json";

    /**
     * Port
     */
    static int port;

    static {

        try {
            // Get a free port
            ServerSocket s = new ServerSocket(0);
            port = s.getLocalPort();
            s.close();

        } catch (IOException e) {
            // No OPS
        }
    }

    /**
     * WireMockRule
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(port);

    /**
     * Given JUnit managed server, when matching URL, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenJUnitManagedServer_whenMatchingURL_thenCorrect() throws IOException {

        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlPathMatching("/baeldung/.*"))
                        .willReturn(
                                WireMock
                                        .aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", APPLICATION_JSON)
                                        .withBody("\"testing-library\": \"WireMock\"")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(String.format("http://localhost:%s/baeldung/wiremock", port));
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH)));
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(APPLICATION_JSON, httpResponse.getFirstHeader("Content-Type").getValue());
        Assert.assertEquals("\"testing-library\": \"WireMock\"", stringResponse);
    }

    /**
     * Given JUnit managed server, when matching headers, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenJUnitManagedServer_whenMatchingHeaders_thenCorrect() throws IOException {
        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlPathEqualTo(BAELDUNG_WIREMOCK_PATH))
                        .withHeader("Accept", WireMock.matching("text/.*"))
                        .willReturn(
                                WireMock
                                        .aResponse()
                                        .withStatus(503)
                                        .withHeader("Content-Type", "text/html")
                                        .withBody("!!! Service Unavailable !!!")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(String.format("http://localhost:%s/baeldung/wiremock", port));
        request.addHeader("Accept", "text/html");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH)));
        Assert.assertEquals(503, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals("text/html", httpResponse.getFirstHeader("Content-Type").getValue());
        Assert.assertEquals("!!! Service Unavailable !!!", stringResponse);
    }

    /**
     * Given JUnit managed server, when matching body, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenJUnitManagedServer_whenMatchingBody_thenCorrect() throws IOException {
        WireMock.stubFor(
                WireMock
                        .post(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH))
                        .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
                        .withRequestBody(WireMock.containing("\"testing-library\": \"WireMock\""))
                        .withRequestBody(WireMock.containing("\"creator\": \"Tom Akehurst\""))
                        .withRequestBody(WireMock.containing("\"website\": \"wiremock.org\""))
                        .willReturn(WireMock.aResponse().withStatus(200)));

        InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("wiremock_intro.json");
        String jsonString = convertInputStreamToString(jsonInputStream);
        StringEntity entity = new StringEntity(jsonString);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(String.format("http://localhost:%s/baeldung/wiremock", port));
        request.addHeader("Content-Type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        WireMock.verify(
                WireMock
                        .postRequestedFor(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH))
                        .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON)));
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Given JUnit managed server, when not using priority, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenJUnitManagedServer_whenNotUsingPriority_thenCorrect() throws IOException {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathMatching("/baeldung/.*")).willReturn(
                        WireMock.aResponse().withStatus(200)));
        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlPathEqualTo(BAELDUNG_WIREMOCK_PATH))
                        .withHeader("Accept", WireMock.matching("text/.*"))
                        .willReturn(WireMock.aResponse().withStatus(503)));

        HttpResponse httpResponse = generateClientAndReceiveResponseForPriorityTests();

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH)));
        Assert.assertEquals(503, httpResponse.getStatusLine().getStatusCode());
    }

    /**
     * Given JUnit managed server, when using priority, then correct
     *
     * @throws IOException when things go wrong
     */
    @Test
    public void givenJUnitManagedServer_whenUsingPriority_thenCorrect() throws IOException {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathMatching("/baeldung/.*")).atPriority(1).willReturn(
                        WireMock.aResponse().withStatus(200)));
        WireMock.stubFor(
                WireMock
                        .get(WireMock.urlPathEqualTo(BAELDUNG_WIREMOCK_PATH))
                        .atPriority(2)
                        .withHeader("Accept", WireMock.matching("text/.*"))
                        .willReturn(WireMock.aResponse().withStatus(503)));

        HttpResponse httpResponse = generateClientAndReceiveResponseForPriorityTests();

        WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo(BAELDUNG_WIREMOCK_PATH)));
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    /**
     * Converts HttpResponse to a string
     *
     * @param httpResponse HttpResponse
     * @return string
     * @throws IOException when things go wrong
     */
    private static String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    /**
     * Converts InputStream to a string
     *
     * @param inputStream InputStream
     * @return string
     */
    private static String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }

    /**
     * Generates client for priority tests
     *
     * @return HttpResponse
     * @throws IOException when things go wrong
     */
    private static HttpResponse generateClientAndReceiveResponseForPriorityTests() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(String.format("http://localhost:%s/baeldung/wiremock", port));
        request.addHeader("Accept", "text/xml");
        return httpClient.execute(request);
    }
}
