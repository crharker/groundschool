/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.util.Date;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.UserEntity;

/**
 * ObjectCreator
 *
 * @author brianmichael
 */
public class ObjectCreator {

    /**
     * ID
     */
    public static final Long ID = 1L;

    /**
     * ACCESS_TOKEN_VALIDITY
     */
    public static final Long ACCESS_TOKEN_VALIDITY = 18000000L;

    /**
     * CONNECT_TIMEOUT
     */
    public static final Integer CONNECT_TIMEOUT = 1000;

    /**
     * READ_TIMEOUT
     */
    public static final Integer READ_TIMEOUT = 10000;

    /**
     * NON_EXISTENT_ID
     */
    public static final Long NON_EXISTENT_ID = 0L;

    /**
     * HEADER_STRING
     */
    public static final String HEADER_STRING = "header string";

    /**
     * SIGNING_KEY
     */
    public static final String SIGNING_KEY = "signing";

    /**
     * AUTHORITIES_KEY
     */
    public static final String AUTHORITIES_KEY = "authorities";

    /**
     * RESOURCE_LOCATION
     */
    public static final String RESOURCE_LOCATION = "http://www.faa.gov";

    /**
     * SID
     */
    public static final String SID = "sid";

    /**
     * Token
     */
    public static final String TOKEN = "token";

    /**
     * TITLE
     */
    public static final String TITLE = "title";

    /**
     * TEXT
     */
    public static final String TEXT = "text";

    /**
     * ROLE
     */
    public static final String ROLE = "ADMIN";

    /**
     * USERNAME
     */
    public static final String USERNAME = "username";

    /**
     * PASSWORD
     */
    public static final String PASSWORD = "password";

    /**
     * ROLE_DESCRIPTION
     */
    public static final String ROLE_DESCRIPTION = "Admin role";

    /**
     * ROLE_NAME
     */
    public static final String ROLE_NAME = "ADMIN";

    /**
     * EMAIL
     */
    public static final String EMAIL = "noone@nowhere.com";

    /**
     * PHONE
     */
    public static final String PHONE = "4048675309";

    /**
     * EAA_NUMBER
     */
    public static final String EAA_NUMBER = "123456";

    /**
     * FIRST_NAME
     */
    public static final String FIRST_NAME = "Bob";

    /**
     * LAST_NAME
     */
    public static final String LAST_NAME = "Smith";

    /**
     * CITY
     */
    public static final String CITY = "city";

    /**
     * STATE
     */
    public static final String STATE = "state";

    /**
     * ZIP_CODE
     */
    public static final String ZIP_CODE = "zip_code";

    /**
     * ADDRESS_LINE_1
     */
    public static final String ADDRESS_LINE_1 = "address line 1";

    /**
     * SPOUSE_NAME
     */
    public static final String SPOUSE_NAME = "spouse";

    /**
     * TAIL_NUMBER
     */
    public static final String TAIL_NUMBER = "tail";

    /**
     * TAIL_NUMBER
     */
    public static final Date CHAPTER_DATE_PAID = new Date();

    /**
     * DATE_PAID
     */
    public static final Date DATE_PAID = new Date();

    /**
     * EAA_MEMBER_EXPIRY_DATE
     */
    public static final Date EAA_MEMBER_EXPIRY_DATE = new Date();

    /**
     * EAA_YOUTH_PROTECTION_EXPIRY_DATE
     */
    public static final Date EAA_YOUTH_PROTECTION_EXPIRY_DATE = new Date();

    /**
     * Gets a staticly defined User
     *
     * @return User
     */
    public static User getUser() {
        User mockUser = new User();
        mockUser.setId(ID);
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setFirstName(FIRST_NAME);
        mockUser.setLastName(LAST_NAME);
        mockUser.setEmail(EMAIL);
        mockUser.setRole(ROLE);
        return mockUser;
    }

    /**
     * Gets a staticly defined UserEntity
     *
     * @return UserEntity
     */
    public static UserEntity getUserEntity() {
        UserEntity mockUser = new UserEntity();
        mockUser.setId(ID);
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setFirstName(FIRST_NAME);
        mockUser.setLastName(LAST_NAME);
        mockUser.setEmail(EMAIL);
        mockUser.setRole(ROLE);
        return mockUser;
    }

}
