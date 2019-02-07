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

}
