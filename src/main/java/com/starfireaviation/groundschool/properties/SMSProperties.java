/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SMSProperties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class SMSProperties {

    /**
     * From address for SMS messages
     */
    @Value("${groundschool.sms.from-address}")
    private String fromAddress;

    /**
     * ACCOUNT_SID for SMS messages
     */
    @Value("${groundschool.sms.account-sid}")
    private String accountSid;

    /**
     * AUTH_ID for SMS messages
     */
    @Value("${groundschool.sms.auth-id}")
    private String authId;

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
     * Retrieves the value for {@link #accountSid}.
     *
     * @return the current value
     */
    public String getAccountSid() {
        return accountSid;
    }

    /**
     * Provides a value for {@link #accountSid}.
     *
     * @param accountSid the new value to set
     */
    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Retrieves the value for {@link #authId}.
     *
     * @return the current value
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * Provides a value for {@link #authId}.
     *
     * @param authId the new value to set
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

}
