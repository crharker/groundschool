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
     * BCC address for emails
     */
    @Value("${groundschool.email.bcc-address}")
    private String bccAddress;

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
     * Retrieves the value for {@link #bccAddress}.
     *
     * @return the current value
     */
    public String getBccAddress() {
        return bccAddress;
    }

    /**
     * Provides a value for {@link #bccAddress}.
     *
     * @param bccAddress the new value to set
     */
    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

}
