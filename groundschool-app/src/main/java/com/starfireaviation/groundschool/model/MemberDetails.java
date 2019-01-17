/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.Date;

/**
 * Answer
 *
 * @author brianmichael
 */
public class MemberDetails extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Local ID
     */
    private Long localId;

    /**
     * User ID
     */
    private Long userId;

    /**
     * First name
     */
    private String firstName;

    /**
     * Last name
     */
    private String lastName;

    /**
     * Address Line 1
     */
    private String addressLine1;

    /**
     * City
     */
    private String city;

    /**
     * State
     */
    private String state;

    /**
     * Zip Code
     */
    private String zipCode;

    /**
     * Home Phone
     */
    private String homePhone;

    /**
     * Cell Phone
     */
    private String cellPhone;

    /**
     * Email
     */
    private String email;

    /**
     * Spouse Name
     */
    private String spouseName;

    /**
     * EAA Number
     */
    private String eaaNumber;

    /**
     * EAA Paid
     */
    private boolean eaaPaid;

    /**
     * EAA Youth Protection
     */
    private boolean eaaYouthProtection;

    /**
     * EAA Youth Protection Expiry Date
     */
    private Date eaaYouthProtectionExpiryDate;

    /**
     * EAA Member Expiry Date
     */
    private Date eaaMemberExpiryDate;

    /**
     * Tail Number
     */
    private String tailNumber;

    /**
     * Chapter Member
     */
    private boolean chapterMember;

    /**
     * Paid Member
     */
    private boolean chapterPaid;

    /**
     * Date chapter membership was paid
     */
    private Date chapterDatePaid;

    /**
     * Membership Type (Individual or Family)
     */
    private ChapterMembershipType chapterMembershipType;

    /**
     * Initializes an instance of <code>MemberDetails</code> with the default data.
     *
     * @param localId local ID
     * @param firstName first name
     * @param lastName last name
     * @param chapterMember chapter member
     */
    public MemberDetails(Long localId, String firstName, String lastName, boolean chapterMember) {
        this.localId = localId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.chapterMember = chapterMember;
    }

    /**
     * Initializes an instance of <code>MemberDetails</code> with the default data.
     */
    public MemberDetails() {
        // Default constructor
    }

    /**
     * Retrieves the value for {@link #localId}.
     *
     * @return the current value
     */
    public Long getLocalId() {
        return localId;
    }

    /**
     * Provides a value for {@link #localId}.
     *
     * @param localId the new value to set
     */
    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    /**
     * Retrieves the value for {@link #userId}.
     *
     * @return the current value
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Provides a value for {@link #userId}.
     *
     * @param userId the new value to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * Retrieves the value for {@link #addressLine1}.
     *
     * @return the current value
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Provides a value for {@link #addressLine1}.
     *
     * @param addressLine1 the new value to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Retrieves the value for {@link #city}.
     *
     * @return the current value
     */
    public String getCity() {
        return city;
    }

    /**
     * Provides a value for {@link #city}.
     *
     * @param city the new value to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the value for {@link #state}.
     *
     * @return the current value
     */
    public String getState() {
        return state;
    }

    /**
     * Provides a value for {@link #state}.
     *
     * @param state the new value to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Retrieves the value for {@link #zipCode}.
     *
     * @return the current value
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Provides a value for {@link #zipCode}.
     *
     * @param zipCode the new value to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Retrieves the value for {@link #homePhone}.
     *
     * @return the current value
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Provides a value for {@link #homePhone}.
     *
     * @param homePhone the new value to set
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * Retrieves the value for {@link #cellPhone}.
     *
     * @return the current value
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * Provides a value for {@link #cellPhone}.
     *
     * @param cellPhone the new value to set
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
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
     * Retrieves the value for {@link #spouseName}.
     *
     * @return the current value
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * Provides a value for {@link #spouseName}.
     *
     * @param spouseName the new value to set
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
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
     * Retrieves the value for {@link #eaaPaid}.
     *
     * @return the current value
     */
    public boolean isEaaPaid() {
        return eaaPaid;
    }

    /**
     * Provides a value for {@link #eaaPaid}.
     *
     * @param eaaPaid the new value to set
     */
    public void setEaaPaid(boolean eaaPaid) {
        this.eaaPaid = eaaPaid;
    }

    /**
     * Retrieves the value for {@link #eaaYouthProtection}.
     *
     * @return the current value
     */
    public boolean isEaaYouthProtection() {
        return eaaYouthProtection;
    }

    /**
     * Provides a value for {@link #eaaYouthProtection}.
     *
     * @param eaaYouthProtection the new value to set
     */
    public void setEaaYouthProtection(boolean eaaYouthProtection) {
        this.eaaYouthProtection = eaaYouthProtection;
    }

    /**
     * Retrieves the value for {@link #eaaYouthProtectionExpiryDate}.
     *
     * @return the current value
     */
    public Date getEaaYouthProtectionExpiryDate() {
        return eaaYouthProtectionExpiryDate;
    }

    /**
     * Provides a value for {@link #eaaYouthProtectionExpiryDate}.
     *
     * @param eaaYouthProtectionExpiryDate the new value to set
     */
    public void setEaaYouthProtectionExpiryDate(Date eaaYouthProtectionExpiryDate) {
        this.eaaYouthProtectionExpiryDate = eaaYouthProtectionExpiryDate;
    }

    /**
     * Retrieves the value for {@link #eaaMemberExpiryDate}.
     *
     * @return the current value
     */
    public Date getEaaMemberExpiryDate() {
        return eaaMemberExpiryDate;
    }

    /**
     * Provides a value for {@link #eaaMemberExpiryDate}.
     *
     * @param eaaMemberExpiryDate the new value to set
     */
    public void setEaaMemberExpiryDate(Date eaaMemberExpiryDate) {
        this.eaaMemberExpiryDate = eaaMemberExpiryDate;
    }

    /**
     * Retrieves the value for {@link #tailNumber}.
     *
     * @return the current value
     */
    public String getTailNumber() {
        return tailNumber;
    }

    /**
     * Provides a value for {@link #tailNumber}.
     *
     * @param tailNumber the new value to set
     */
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    /**
     * Retrieves the value for {@link #chapterMember}.
     *
     * @return the current value
     */
    public boolean isChapterMember() {
        return chapterMember;
    }

    /**
     * Provides a value for {@link #chapterMember}.
     *
     * @param chapterMember the new value to set
     */
    public void setChapterMember(boolean chapterMember) {
        this.chapterMember = chapterMember;
    }

    /**
     * Retrieves the value for {@link #chapterPaid}.
     *
     * @return the current value
     */
    public boolean isChapterPaid() {
        return chapterPaid;
    }

    /**
     * Provides a value for {@link #chapterPaid}.
     *
     * @param chapterPaid the new value to set
     */
    public void setChapterPaid(boolean chapterPaid) {
        this.chapterPaid = chapterPaid;
    }

    /**
     * Retrieves the value for {@link #chapterDatePaid}.
     *
     * @return the current value
     */
    public Date getChapterDatePaid() {
        return chapterDatePaid;
    }

    /**
     * Provides a value for {@link #chapterDatePaid}.
     *
     * @param chapterDatePaid the new value to set
     */
    public void setChapterDatePaid(Date chapterDatePaid) {
        this.chapterDatePaid = chapterDatePaid;
    }

    /**
     * Retrieves the value for {@link #chapterMembershipType}.
     *
     * @return the current value
     */
    public ChapterMembershipType getChapterMembershipType() {
        return chapterMembershipType;
    }

    /**
     * Provides a value for {@link #chapterMembershipType}.
     *
     * @param chapterMembershipType the new value to set
     */
    public void setChapterMembershipType(ChapterMembershipType chapterMembershipType) {
        this.chapterMembershipType = chapterMembershipType;
    }
}