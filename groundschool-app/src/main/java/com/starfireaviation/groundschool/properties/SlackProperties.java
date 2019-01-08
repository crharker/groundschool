/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SlackProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class SlackProperties {

    /**
     * From address for Slack messages
     */
    @Value("${groundschool.slack.from-address}")
    private String fromAddress;

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

}
