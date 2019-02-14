/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ApplicationProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class ApplicationProperties {

    /**
     * APPLICATION_HOST
     */
    @Value("${application.host}")
    private String applicationHost;

    /**
     * Secret
     */
    @Value("${application.security.secret}")
    private String secret;

    /**
     * Expiration Time
     */
    @Value("${application.security.expiration-time}")
    private long expirationTime;

    /**
     * Retrieves the value for {@link #applicationHost}.
     *
     * @return the current value
     */
    public String getApplicationHost() {
        return applicationHost;
    }

    /**
     * Provides a value for {@link #applicationHost}.
     *
     * @param applicationHost the new value to set
     */
    public void setApplicationHost(String applicationHost) {
        this.applicationHost = applicationHost;
    }

    /**
     * Retrieves the value for {@link #secret}.
     *
     * @return the current value
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Provides a value for {@link #secret}.
     *
     * @param secret the new value to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Retrieves the value for {@link #expirationTime}.
     *
     * @return the current value
     */
    public long getExpirationTime() {
        return expirationTime;
    }

    /**
     * Provides a value for {@link #expirationTime}.
     *
     * @param expirationTime the new value to set
     */
    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

}
