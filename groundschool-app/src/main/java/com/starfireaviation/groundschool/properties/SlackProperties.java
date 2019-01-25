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
     * Slack BOT Token
     */
    @Value("${slackBotToken}")
    private String token;

    /**
     * Incoming Webhook URL
     *
     * The Url you get while configuring a new incoming webhook on Slack. You can setup a new incoming webhook
     * <a href="https://my.slack.com/services/new/incoming-webhook/">here</a>.
     */
    @Value("${slackIncomingWebhookUrl}")
    private String webhookUrl;

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

    /**
     * Retrieves the value for {@link #webhookUrl}.
     *
     * @return the current value
     */
    public String getWebhookUrl() {
        return webhookUrl;
    }

    /**
     * Provides a value for {@link #webhookUrl}.
     *
     * @param webhookUrl the new value to set
     */
    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

}
