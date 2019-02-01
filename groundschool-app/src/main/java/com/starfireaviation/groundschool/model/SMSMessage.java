/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.net.URLDecoder;

/**
 * Message
 *
 * @author brianmichael
 */
public class SMSMessage {

    /**
     * UTF-8
     */
    public static final String UTF8 = "UTF-8";

    /**
     * ToCountry
     */
    private String toCountry;

    /**
     * ToState
     */
    private String toState;

    /**
     * SmsMessageSid
     */
    private String smsMessageSid;

    /**
     * NumMedia
     */
    private String numMedia;

    /**
     * ToCity
     */
    private String toCity;

    /**
     * FromZip
     */
    private String fromZip;

    /**
     * SmsSid
     */
    private String smsSid;

    /**
     * FromState
     */
    private String fromState;

    /**
     * SmsStatus
     */
    private String smsStatus;

    /**
     * FromCity
     */
    private String fromCity;

    /**
     * Body
     */
    private String body;

    /**
     * FromCountry
     */
    private String fromCountry;

    /**
     * To
     */
    private String destination;

    /**
     * ToZip
     */
    private String toZip;

    /**
     * NumSegments
     */
    private String numSegments;

    /**
     * MessageSid
     */
    private String messageSid;

    /**
     * AccountSid
     */
    private String accountSid;

    /**
     * From
     */
    private String from;

    /**
     * ApiVersion
     */
    private String apiVersion;

    /**
     * Initializes an instance of <code>SMSMessage</code> with the default data.
     */
    public SMSMessage() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>SMSMessage</code> with the default data.
     *
     * @param message to be parsed
     */
    public SMSMessage(String message) {
        if (message != null) {
            String[] parts = message.split("&");
            for (String part : parts) {
                try {
                    String[] kvPair = part.split("=");
                    TwilioPart twilioPart = TwilioPart.valueOf(kvPair[0]);
                    switch (twilioPart) {
                        case ToCountry:
                            toCountry = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case ToState:
                            toState = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case SmsMessageSid:
                            smsMessageSid = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case NumMedia:
                            numMedia = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case ToCity:
                            toCity = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case FromZip:
                            fromZip = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case SmsSid:
                            smsSid = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case FromState:
                            fromState = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case SmsStatus:
                            smsStatus = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case FromCity:
                            fromCity = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case Body:
                            body = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case FromCountry:
                            fromCountry = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case To:
                            destination = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case ToZip:
                            toZip = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case NumSegments:
                            numSegments = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case MessageSid:
                            messageSid = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case AccountSid:
                            accountSid = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case From:
                            from = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        case ApiVersion:
                            apiVersion = URLDecoder.decode(kvPair[1], UTF8);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    // Do nothing
                }
            }
        }
    }

    /**
     * Retrieves the value for {@link #toCountry}.
     *
     * @return the current value
     */
    public String getToCountry() {
        return toCountry;
    }

    /**
     * Provides a value for {@link #toCountry}.
     *
     * @param toCountry the new value to set
     */
    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    /**
     * Retrieves the value for {@link #toState}.
     *
     * @return the current value
     */
    public String getToState() {
        return toState;
    }

    /**
     * Provides a value for {@link #toState}.
     *
     * @param toState the new value to set
     */
    public void setToState(String toState) {
        this.toState = toState;
    }

    /**
     * Retrieves the value for {@link #smsMessageSid}.
     *
     * @return the current value
     */
    public String getSmsMessageSid() {
        return smsMessageSid;
    }

    /**
     * Provides a value for {@link #smsMessageSid}.
     *
     * @param smsMessageSid the new value to set
     */
    public void setSmsMessageSid(String smsMessageSid) {
        this.smsMessageSid = smsMessageSid;
    }

    /**
     * Retrieves the value for {@link #numMedia}.
     *
     * @return the current value
     */
    public String getNumMedia() {
        return numMedia;
    }

    /**
     * Provides a value for {@link #numMedia}.
     *
     * @param numMedia the new value to set
     */
    public void setNumMedia(String numMedia) {
        this.numMedia = numMedia;
    }

    /**
     * Retrieves the value for {@link #toCity}.
     *
     * @return the current value
     */
    public String getToCity() {
        return toCity;
    }

    /**
     * Provides a value for {@link #toCity}.
     *
     * @param toCity the new value to set
     */
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    /**
     * Retrieves the value for {@link #fromZip}.
     *
     * @return the current value
     */
    public String getFromZip() {
        return fromZip;
    }

    /**
     * Provides a value for {@link #fromZip}.
     *
     * @param fromZip the new value to set
     */
    public void setFromZip(String fromZip) {
        this.fromZip = fromZip;
    }

    /**
     * Retrieves the value for {@link #smsSid}.
     *
     * @return the current value
     */
    public String getSmsSid() {
        return smsSid;
    }

    /**
     * Provides a value for {@link #smsSid}.
     *
     * @param smsSid the new value to set
     */
    public void setSmsSid(String smsSid) {
        this.smsSid = smsSid;
    }

    /**
     * Retrieves the value for {@link #fromState}.
     *
     * @return the current value
     */
    public String getFromState() {
        return fromState;
    }

    /**
     * Provides a value for {@link #fromState}.
     *
     * @param fromState the new value to set
     */
    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    /**
     * Retrieves the value for {@link #smsStatus}.
     *
     * @return the current value
     */
    public String getSmsStatus() {
        return smsStatus;
    }

    /**
     * Provides a value for {@link #smsStatus}.
     *
     * @param smsStatus the new value to set
     */
    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    /**
     * Retrieves the value for {@link #fromCity}.
     *
     * @return the current value
     */
    public String getFromCity() {
        return fromCity;
    }

    /**
     * Provides a value for {@link #fromCity}.
     *
     * @param fromCity the new value to set
     */
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    /**
     * Retrieves the value for {@link #body}.
     *
     * @return the current value
     */
    public String getBody() {
        return body;
    }

    /**
     * Provides a value for {@link #body}.
     *
     * @param body the new value to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Retrieves the value for {@link #fromCountry}.
     *
     * @return the current value
     */
    public String getFromCountry() {
        return fromCountry;
    }

    /**
     * Provides a value for {@link #fromCountry}.
     *
     * @param fromCountry the new value to set
     */
    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    /**
     * Retrieves the value for {@link #destination}.
     *
     * @return the current value
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Provides a value for {@link #destination}.
     *
     * @param destination the new value to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Retrieves the value for {@link #toZip}.
     *
     * @return the current value
     */
    public String getToZip() {
        return toZip;
    }

    /**
     * Provides a value for {@link #toZip}.
     *
     * @param toZip the new value to set
     */
    public void setToZip(String toZip) {
        this.toZip = toZip;
    }

    /**
     * Retrieves the value for {@link #numSegments}.
     *
     * @return the current value
     */
    public String getNumSegments() {
        return numSegments;
    }

    /**
     * Provides a value for {@link #numSegments}.
     *
     * @param numSegments the new value to set
     */
    public void setNumSegments(String numSegments) {
        this.numSegments = numSegments;
    }

    /**
     * Retrieves the value for {@link #messageSid}.
     *
     * @return the current value
     */
    public String getMessageSid() {
        return messageSid;
    }

    /**
     * Provides a value for {@link #messageSid}.
     *
     * @param messageSid the new value to set
     */
    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
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
     * Retrieves the value for {@link #from}.
     *
     * @return the current value
     */
    public String getFrom() {
        return from;
    }

    /**
     * Provides a value for {@link #from}.
     *
     * @param from the new value to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Retrieves the value for {@link #apiVersion}.
     *
     * @return the current value
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Provides a value for {@link #apiVersion}.
     *
     * @param apiVersion the new value to set
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String toString() {
        return "SMSMessage ["
                + (toCountry != null ? "toCountry=" + toCountry + ", " : "")
                + (toState != null ? "toState=" + toState + ", " : "")
                + (smsMessageSid != null ? "smsMessageSid=" + smsMessageSid + ", " : "")
                + (numMedia != null ? "numMedia=" + numMedia + ", " : "")
                + (toCity != null ? "toCity=" + toCity + ", " : "")
                + (fromZip != null ? "fromZip=" + fromZip + ", " : "")
                + (smsSid != null ? "smsSid=" + smsSid + ", " : "")
                + (fromState != null ? "fromState=" + fromState + ", " : "")
                + (smsStatus != null ? "smsStatus=" + smsStatus + ", " : "")
                + (fromCity != null ? "fromCity=" + fromCity + ", " : "")
                + (body != null ? "body=" + body + ", " : "")
                + (fromCountry != null ? "fromCountry=" + fromCountry + ", " : "")
                + (destination != null ? "to=" + destination + ", " : "")
                + (toZip != null ? "toZip=" + toZip + ", " : "")
                + (numSegments != null ? "numSegments=" + numSegments + ", " : "")
                + (messageSid != null ? "messageSid=" + messageSid + ", " : "")
                + (accountSid != null ? "accountSid=" + accountSid + ", " : "")
                + (from != null ? "from=" + from + ", " : "")
                + (apiVersion != null ? "apiVersion=" + apiVersion : "")
                + "]";
    }

}
