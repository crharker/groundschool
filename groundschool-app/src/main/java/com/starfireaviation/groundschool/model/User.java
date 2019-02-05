/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

/**
 * User
 *
 * @author brianmichael
 */
public class User extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Email
     */
    private String email;

    /**
     * Email verified
     */
    private boolean emailVerified;

    /**
     * Email enabled
     */
    private boolean emailEnabled;

    /**
     * SMS
     */
    private String sms;

    /**
     * SMS verified
     */
    private boolean smsVerified;

    /**
     * SMS enabled
     */
    private boolean smsEnabled;

    /**
     * Slack
     */
    private String slack;

    /**
     * Slack verified
     */
    private boolean slackVerified;

    /**
     * Slack enabled
     */
    private boolean slackEnabled;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * First name
     */
    private String firstName;

    /**
     * Last name
     */
    private String lastName;

    /**
     * EAA Number
     */
    private String eaaNumber;

    /**
     * Certificate Number
     */
    private String certificateNumber;

    /**
     * Code for verification purposes
     */
    private String code;

    /**
     * Role
     */
    private Role role;

    /**
     * QuestionPreference
     */
    private QuestionPreference questionPreference;

    /**
     * Retrieves the value for {@link #username}.
     *
     * @return the current value
     */
    public String getUsername() {
        return username;
    }

    /**
     * Provides a value for {@link #username}.
     *
     * @param username the new value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the value for {@link #password}.
     *
     * @return the current value
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a value for {@link #password}.
     *
     * @param password the new value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the value for {@link #role}.
     *
     * @return the current value
     */
    public Role getRole() {
        return role;
    }

    /**
     * Provides a value for {@link #role}.
     *
     * @param role the new value to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Retrieves the value for {@link #email}.
     *
     * @return the current value
     */
    public String getEmail() {
        return email;
    }

    /**
     * Provides a value for {@link #email}.
     *
     * @param email the new value to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the value for {@link #firstName}.
     *
     * @return the current value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Provides a value for {@link #firstName}.
     *
     * @param firstName the new value to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the value for {@link #lastName}.
     *
     * @return the current value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Provides a value for {@link #lastName}.
     *
     * @param lastName the new value to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the value for {@link #emailVerified}.
     *
     * @return the current value
     */
    public boolean isEmailVerified() {
        return emailVerified;
    }

    /**
     * Provides a value for {@link #emailVerified}.
     *
     * @param emailVerified the new value to set
     */
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * Retrieves the value for {@link #sms}.
     *
     * @return the current value
     */
    public String getSms() {
        return sms;
    }

    /**
     * Provides a value for {@link #sms}.
     *
     * @param sms the new value to set
     */
    public void setSms(String sms) {
        this.sms = sms;
    }

    /**
     * Retrieves the value for {@link #smsVerified}.
     *
     * @return the current value
     */
    public boolean isSmsVerified() {
        return smsVerified;
    }

    /**
     * Provides a value for {@link #smsVerified}.
     *
     * @param smsVerified the new value to set
     */
    public void setSmsVerified(boolean smsVerified) {
        this.smsVerified = smsVerified;
    }

    /**
     * Retrieves the value for {@link #slack}.
     *
     * @return the current value
     */
    public String getSlack() {
        return slack;
    }

    /**
     * Provides a value for {@link #slack}.
     *
     * @param slack the new value to set
     */
    public void setSlack(String slack) {
        this.slack = slack;
    }

    /**
     * Retrieves the value for {@link #slackVerified}.
     *
     * @return the current value
     */
    public boolean isSlackVerified() {
        return slackVerified;
    }

    /**
     * Provides a value for {@link #slackVerified}.
     *
     * @param slackVerified the new value to set
     */
    public void setSlackVerified(boolean slackVerified) {
        this.slackVerified = slackVerified;
    }

    /**
     * Retrieves the value for {@link #emailEnabled}.
     *
     * @return the current value
     */
    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    /**
     * Provides a value for {@link #emailEnabled}.
     *
     * @param emailEnabled the new value to set
     */
    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    /**
     * Retrieves the value for {@link #smsEnabled}.
     *
     * @return the current value
     */
    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    /**
     * Provides a value for {@link #smsEnabled}.
     *
     * @param smsEnabled the new value to set
     */
    public void setSmsEnabled(boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    /**
     * Retrieves the value for {@link #slackEnabled}.
     *
     * @return the current value
     */
    public boolean isSlackEnabled() {
        return slackEnabled;
    }

    /**
     * Provides a value for {@link #slackEnabled}.
     *
     * @param slackEnabled the new value to set
     */
    public void setSlackEnabled(boolean slackEnabled) {
        this.slackEnabled = slackEnabled;
    }

    /**
     * Retrieves the value for {@link #eaaNumber}.
     *
     * @return the current value
     */
    public String getEaaNumber() {
        return eaaNumber;
    }

    /**
     * Provides a value for {@link #eaaNumber}.
     *
     * @param eaaNumber the new value to set
     */
    public void setEaaNumber(String eaaNumber) {
        this.eaaNumber = eaaNumber;
    }

    /**
     * Retrieves the value for {@link #certificateNumber}.
     *
     * @return the current value
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * Provides a value for {@link #certificateNumber}.
     *
     * @param certificateNumber the new value to set
     */
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    /**
     * Retrieves the value for {@link #code}.
     *
     * @return the current value
     */
    public String getCode() {
        return code;
    }

    /**
     * Provides a value for {@link #code}.
     *
     * @param code the new value to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the value for {@link #questionPreference}.
     *
     * @return the current value
     */
    public QuestionPreference getQuestionPreference() {
        return questionPreference;
    }

    /**
     * Provides a value for {@link #questionPreference}.
     *
     * @param questionPreference the new value to set
     */
    public void setQuestionPreference(QuestionPreference questionPreference) {
        this.questionPreference = questionPreference;
    }

}
