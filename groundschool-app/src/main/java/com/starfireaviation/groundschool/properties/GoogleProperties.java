/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * EmailProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class GoogleProperties {

    /**
     * Client ID
     */
    @Value("${google.client.client-id}")
    private String clientId;

    /**
     * Client Secret
     */
    @Value("${google.client.client-secret}")
    private String clientSecret;

    /**
     * Redirect URI
     */
    @Value("${google.client.redirectUri}")
    private String redirectUri;

    /**
     * Retrieves the value for {@link #clientId}.
     *
     * @return the current value
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Provides a value for {@link #clientId}.
     *
     * @param clientId the new value to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the value for {@link #clientSecret}.
     *
     * @return the current value
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Provides a value for {@link #clientSecret}.
     *
     * @param clientSecret the new value to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Retrieves the value for {@link #redirectUri}.
     *
     * @return the current value
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * Provides a value for {@link #redirectUri}.
     *
     * @param redirectUri the new value to set
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

}
