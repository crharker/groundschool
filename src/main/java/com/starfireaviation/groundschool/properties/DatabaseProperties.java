/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DatabaseProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class DatabaseProperties {

    /**
     * Url
     */
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Username
     */
    @Value("${spring.datasource.username}")
    private String username;

    /**
     * Password
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Driver
     */
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    /**
     * Retrieves the value for {@link #url}.
     *
     * @return the current value
     */
    public String getUrl() {
        return url;
    }

    /**
     * Provides a value for {@link #url}.
     *
     * @param url the new value to set
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * Retrieves the value for {@link #driver}.
     *
     * @return the current value
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Provides a value for {@link #driver}.
     *
     * @param driver the new value to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

}
