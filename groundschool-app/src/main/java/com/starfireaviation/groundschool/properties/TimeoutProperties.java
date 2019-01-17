/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TimeoutProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class TimeoutProperties {

    /**
     * Read timeout for RestTemplate
     */
    @Value("${groundschool.timeout.read}")
    private int read;

    /**
     * Connect timeout for RestTemplate
     */
    @Value("${groundschool.timeout.connect}")
    private int connect;

    /**
     * Access token validity in ms
     */
    @Value("${groundschool.timeout.access-token-validity}")
    private long accessTokenValidity;

    /**
     * Retrieves the value for {@link #read}.
     *
     * @return the current value
     */
    public int getRead() {
        return read;
    }

    /**
     * Provides a value for {@link #read}.
     *
     * @param read the new value to set
     */
    public void setRead(int read) {
        this.read = read;
    }

    /**
     * Retrieves the value for {@link #connect}.
     *
     * @return the current value
     */
    public int getConnect() {
        return connect;
    }

    /**
     * Provides a value for {@link #connect}.
     *
     * @param connect the new value to set
     */
    public void setConnect(int connect) {
        this.connect = connect;
    }

    /**
     * Retrieves the value for {@link #accessTokenValidity}.
     *
     * @return the current value
     */
    public long getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * Provides a value for {@link #accessTokenValidity}.
     *
     * @param accessTokenValidity the new value to set
     */
    public void setAccessTokenValidity(long accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }
}
