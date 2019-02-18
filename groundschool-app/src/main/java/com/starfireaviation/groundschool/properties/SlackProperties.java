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
     * Slack BOT Token
     */
    @Value("${slack.bot.key}")
    private String token;

    /**
     * Retrieves the value for {@link #token}.
     *
     * @return the current value
     */
    public String getToken() {
        return token;
    }

    /**
     * Provides a value for {@link #token}.
     *
     * @param token the new value to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
