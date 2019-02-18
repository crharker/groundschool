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
public class EmailProperties {

    /**
     * From address for emails
     */
    @Value("${groundschool.email.from-address}")
    private String fromAddress;

    /**
     * Host
     */
    @Value("${groundschool.email.host}")
    private String host;

    /**
     * Port
     */
    @Value("${groundschool.email.port}")
    private Integer port;

    /**
     * Username
     */
    @Value("${groundschool.email.username}")
    private String username;

    /**
     * Password
     */
    @Value("${groundschool.email.password}")
    private String password;

    /**
     * Retrieves the value for {@link #fromAddress}.
     *
     * @return the current value
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Provides a value for {@link #fromAddress}.
     *
     * @param fromAddress the new value to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Retrieves the value for {@link #host}.
     *
     * @return the current value
     */
    public String getHost() {
        return host;
    }

    /**
     * Provides a value for {@link #host}.
     *
     * @param host the new value to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Retrieves the value for {@link #port}.
     *
     * @return the current value
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Provides a value for {@link #port}.
     *
     * @param port the new value to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Retrieves the value for {@link #username}.
     *
     * @return the current value
     */
    public String getUsername() {
        return username;
    }

    /**
     * Provides a value for {@link #username}.
     *
     * @param username the new value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the value for {@link #password}.
     *
     * @return the current value
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a value for {@link #password}.
     *
     * @param password the new value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
