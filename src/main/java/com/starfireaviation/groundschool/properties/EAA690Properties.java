/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * EAA690Properties
 *
 * @author brianmichael
 */
@ConfigurationProperties
public class EAA690Properties {

    /**
     * SIGNING_KEY
     */
    @Value("${EAA690.authentication.signing-key}")
    private String signingKey;

    /**
     * AUTHORITIES_KEY
     */
    @Value("${EAA690.authentication.authorities-key}")
    private String authoritiesKey;

    /**
     * HEADER_STRING
     */
    @Value("${EAA690.authentication.header-string}")
    private String headerString;

    /**
     * TOKEN_PREFIX
     */
    @Value("${EAA690.authentication.token-prefix}")
    private String tokenPrefix;

    /**
     * Username for http://eaa690.net
     */
    @Value("${EAA690.authentication.username}")
    private String eaa690NetUsername;

    /**
     * Password for http://eaa690.net
     */
    @Value("${EAA690.authentication.password}")
    private String eaa690NetPassword;

    /**
     * Retrieves the value for {@link #eaa690NetUsername}.
     *
     * @return the current value
     */
    public String getEaa690NetUsername() {
        return eaa690NetUsername;
    }

    /**
     * Provides a value for {@link #eaa690NetUsername}.
     *
     * @param eaa690NetUsername the new value to set
     */
    public void setEaa690NetUsername(String eaa690NetUsername) {
        this.eaa690NetUsername = eaa690NetUsername;
    }

    /**
     * Retrieves the value for {@link #eaa690NetPassword}.
     *
     * @return the current value
     */
    public String getEaa690NetPassword() {
        return eaa690NetPassword;
    }

    /**
     * Provides a value for {@link #eaa690NetPassword}.
     *
     * @param eaa690NetPassword the new value to set
     */
    public void setEaa690NetPassword(String eaa690NetPassword) {
        this.eaa690NetPassword = eaa690NetPassword;
    }

    /**
     * Retrieves the value for {@link #signingKey}.
     *
     * @return the current value
     */
    public String getSigningKey() {
        return signingKey;
    }

    /**
     * Provides a value for {@link #signingKey}.
     *
     * @param signingKey the new value to set
     */
    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    /**
     * Retrieves the value for {@link #authoritiesKey}.
     *
     * @return the current value
     */
    public String getAuthoritiesKey() {
        return authoritiesKey;
    }

    /**
     * Provides a value for {@link #authoritiesKey}.
     *
     * @param authoritiesKey the new value to set
     */
    public void setAuthoritiesKey(String authoritiesKey) {
        this.authoritiesKey = authoritiesKey;
    }

    /**
     * Retrieves the value for {@link #headerString}.
     *
     * @return the current value
     */
    public String getHeaderString() {
        return headerString;
    }

    /**
     * Provides a value for {@link #headerString}.
     *
     * @param headerString the new value to set
     */
    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    /**
     * Retrieves the value for {@link #tokenPrefix}.
     *
     * @return the current value
     */
    public String getTokenPrefix() {
        return tokenPrefix;
    }

    /**
     * Provides a value for {@link #tokenPrefix}.
     *
     * @param tokenPrefix the new value to set
     */
    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}
