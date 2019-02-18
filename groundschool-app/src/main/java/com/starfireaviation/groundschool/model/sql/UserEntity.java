/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity {

    /**
     * Email
     */
    @Column(name = "email")
    private String email;

    /**
     * SMS
     */
    @Column(name = "sms")
    private String sms;

    /**
     * Slack
     */
    @Column(name = "slack")
    private String slack;

    /**
     * Email verified
     */
    @Column(name = "email_verified")
    private boolean emailVerified;

    /**
     * SMS verified
     */
    @Column(name = "sms_verified")
    private boolean smsVerified;

    /**
     * Slack verified
     */
    @Column(name = "slack_verified")
    private boolean slackVerified;

    /**
     * Email enalbed
     */
    @Column(name = "email_enabled")
    private boolean emailEnabled;

    /**
     * SMS enabled
     */
    @Column(name = "sms_enabled")
    private boolean smsEnabled;

    /**
     * Slack enabled
     */
    @Column(name = "slack_enabled")
    private boolean slackEnabled;

    /**
     * Youth Program Participant
     */
    @Column(name = "youth_program_participant")
    private boolean youthProgramParticipant;

    /**
     * Username
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Password
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * EAA Number
     */
    @Column(name = "eaa_number")
    private String eaaNumber;

    /**
     * Certificate Number
     */
    @Column(name = "certificate_number")
    private String certificateNumber;

    /**
     * Code for verification purposes
     */
    @Column(name = "code")
    private String code;

    /**
     * Enabled - used by spring security
     */
    @Column(name = "enabled")
    private int enabled;

    /**
     * Role
     */
    @Column(name = "role", nullable = false)
    private String role;

    /**
     * QuestionPreference
     */
    @Column(name = "question_preference", nullable = false)
    private String questionPreference;

    /**
     * Initializes an instance of <code>UserEntity</code> with the default data.
     */
    public UserEntity() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserEntity</code> with the default data.
     *
     * @param user User
     */
    public UserEntity(UserEntity user) {
        code = user.getCode();
        eaaNumber = user.getEaaNumber();
        email = user.getEmail();
        emailEnabled = user.isEmailEnabled();
        emailVerified = user.isEmailVerified();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        password = user.getPassword();
        role = user.getRole();
        slack = user.getSlack();
        slackEnabled = user.isSlackEnabled();
        slackVerified = user.isSmsVerified();
        sms = user.getSms();
        smsEnabled = user.isSmsEnabled();
        smsVerified = user.isSmsVerified();
        username = user.getUsername();
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
    public String getRole() {
        return role;
    }

    /**
     * Provides a value for {@link #role}.
     *
     * @param role the new value to set
     */
    public void setRole(String role) {
        this.role = role;
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
     * Retrieves the value for {@link #enabled}.
     *
     * @return the current value
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * Provides a value for {@link #enabled}.
     *
     * @param enabled the new value to set
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * Retrieves the value for {@link #questionPreference}.
     *
     * @return the current value
     */
    public String getQuestionPreference() {
        return questionPreference;
    }

    /**
     * Provides a value for {@link #questionPreference}.
     *
     * @param questionPreference the new value to set
     */
    public void setQuestionPreference(String questionPreference) {
        this.questionPreference = questionPreference;
    }

    /**
     * Retrieves the value for {@link #youthProgramParticipant}.
     *
     * @return the current value
     */
    public boolean isYouthProgramParticipant() {
        return youthProgramParticipant;
    }

    /**
     * Provides a value for {@link #youthProgramParticipant}.
     *
     * @param youthProgramParticipant the new value to set
     */
    public void setYouthProgramParticipant(boolean youthProgramParticipant) {
        this.youthProgramParticipant = youthProgramParticipant;
    }

}
