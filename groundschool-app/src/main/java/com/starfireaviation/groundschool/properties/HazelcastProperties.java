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
public class HazelcastProperties {

    /**
     * Cache entry TTL
     */
    @Value("${hazelcast.cache.ttl}")
    private int ttl;

    /**
     * Retrieves the value for {@link #ttl}.
     *
     * @return the current value
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Provides a value for {@link #ttl}.
     *
     * @param ttl the new value to set
     */
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

}
