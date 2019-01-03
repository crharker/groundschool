/*
 * -----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 * -----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.constants;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Service test properties
 *
 * @author brianmichael
 */
@ConfigurationProperties("test.adapter")
public class ServiceTestProperties {

    /**
     * URL application to test.
     */
    private URI uri;

    /**
     * Retrieves the value for {@link #uri}.
     *
     * @return the current value
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Provides a value for {@link #uri}.
     *
     * @param uri the new value to set
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

}
