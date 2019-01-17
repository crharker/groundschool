/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AuthorizationProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class AuthorizationProperties {

    /**
     * Administrator password
     */
    @Value("${groundschool.admin.password}")
    private String adminPassword;

    /**
     * Retrieves the value for {@link #adminPassword}.
     *
     * @return the current value
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * Provides a value for {@link #adminPassword}.
     *
     * @param adminPassword the new value to set
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
