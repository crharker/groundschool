/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starfireaviation.groundschool.model.MemberDetails;

/**
 * HtmlResponseParserTest
 *
 * @author brianmichael
 */
public class HtmlResponseParserTest {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlResponseParserTest.class);

    /**
     * EAA690 member list response string
     */
    private String eaa690MemberListResponseStr = null;

    /**
     * EAA690 non-member list response string
     */
    private String eaa690NonMemberListResponseStr = null;

    /**
     * EAA690 MemberDetails response string
     */
    private String eaa690MemberDetailsResponseStr = null;

    /**
     * EAA690 MemberDetails response string
     */
    private String eaa690MemberDetailsInvalidResponseStr = null;

    /**
     * EAA690 MemberDetails response string
     */
    private String eaa690MemberDetailsInvalidDateResponseStr = null;

    /**
     * EAA690 Login response string
     */
    private String eaa690NetLoginResponseStr = null;

    /**
     * Initializes tests
     */
    @Before
    public void init() {
        eaa690NetLoginResponseStr = readResponseHtml("eaa690_net_login_response.html");
        eaa690NonMemberListResponseStr = readResponseHtml("eaa690_net_non_member_list_response.html");
        eaa690MemberListResponseStr = readResponseHtml("eaa690_net_member_list_response.html");
        eaa690MemberDetailsResponseStr = readResponseHtml("eaa690_net_member_details_response.html");
        eaa690MemberDetailsInvalidResponseStr = readResponseHtml("eaa690_net_member_details_invalid_response.html");
        eaa690MemberDetailsInvalidDateResponseStr = readResponseHtml(
                "eaa690_net_member_details_invalid_date_response.html");
    }

    /**
     * Test parsing of EAA690 MemberDetails response
     */
    @Test
    public void testParseEAA690MemberDetailsResponse() {

        MemberDetails memberDetails = HtmlResponseParser.parseEAA690MemberDetailsResponse(
                eaa690MemberDetailsResponseStr);

        Assert.assertNotNull("MemberDetails is null", memberDetails);
        Assert.assertNotNull("MemberDetails.addressLine1 is null", memberDetails.getAddressLine1());
        Assert.assertNotNull("MemberDetails.city is null", memberDetails.getCity());
        Assert.assertNotNull("MemberDetails.state is null", memberDetails.getState());
        Assert.assertNotNull("MemberDetails.zipCode is null", memberDetails.getZipCode());
        Assert.assertNotNull("MemberDetails.homePhone is null", memberDetails.getHomePhone());
        Assert.assertNotNull("MemberDetails.cellPhone is null", memberDetails.getCellPhone());
        Assert.assertNotNull("MemberDetails.email is null", memberDetails.getEmail());
        Assert.assertNotNull("MemberDetails.spouseName is null", memberDetails.getSpouseName());
        Assert.assertNotNull("MemberDetails.eaaNumber is null", memberDetails.getEaaNumber());
        Assert.assertNotNull("MemberDetails.tailNumber is null", memberDetails.getTailNumber());
        Assert.assertTrue("MemberDetails.member is false", memberDetails.isChapterMember());
        Assert.assertFalse("MemberDetails.paid is false", memberDetails.isChapterPaid());
        Assert.assertNotNull("MemberDetails.datePaid is null", memberDetails.getChapterDatePaid());
        Assert.assertNotNull("MemberDetails.membershipType is null", memberDetails.getChapterMembershipType());
    }

    /**
     * Test parsing of max page number from EAA690.net members HTTP response
     */
    @Test
    public void parseMaxPageNumberFromEAA690MembersResponse() {

        int maxPageNumber = HtmlResponseParser.parseMaxPageNumberFromEAA690NetResponse(
                eaa690MemberListResponseStr);

        Assert.assertTrue("Max page number returned was " + maxPageNumber, maxPageNumber == 11);
    }

    /**
     * Test parsing of max page number from EAA690.net non-members HTTP response
     */
    @Test
    public void parseMaxPageNumberFromEAA690NonMembersResponse() {

        int maxPageNumber = HtmlResponseParser.parseMaxPageNumberFromEAA690NetResponse(
                eaa690NonMemberListResponseStr);

        Assert.assertTrue("Max page number returned was " + maxPageNumber, maxPageNumber == 11);
    }

    /**
     * Test parsing of non-member details from EAA690.net members HTTP response IndexOutOfBoundsException
     */
    @Test
    public void parseNonMemberDetailsFromEAA690MembersResponseIndexOutOfBoundsException() {

        List<MemberDetails> memberDetails = HtmlResponseParser.parseEAA690NonMembersResponse(
                eaa690NetLoginResponseStr);

        Assert.assertNotNull("Members details list was null", memberDetails);
        Assert.assertTrue("Members details list has info", memberDetails.size() <= 0);
    }

    /**
     * Test parsing of member details from EAA690.net members HTTP response
     */
    @Test
    public void parseMemberDetailsFromEAA690MembersResponse() {

        List<MemberDetails> memberDetails = HtmlResponseParser.parseEAA690MembersResponse(
                eaa690MemberListResponseStr);

        Assert.assertNotNull("Members details list was null", memberDetails);
        Assert.assertTrue("Members details list has no info", memberDetails.size() > 0);
    }

    /**
     * Test parsing of member details from EAA690.net members HTTP response IndexOutOfBoundsException
     */
    @Test
    public void parseMemberDetailsFromEAA690MembersResponseIndexOutOfBoundsException() {

        List<MemberDetails> memberDetails = HtmlResponseParser.parseEAA690MembersResponse(
                eaa690NetLoginResponseStr);

        Assert.assertNotNull("Members details list was null", memberDetails);
        Assert.assertTrue("Members details list has info", memberDetails.size() <= 0);
    }

    /**
     * Test parsing of member details from EAA690.net non-members HTTP response
     */
    @Test
    public void parseMemberDetailsFromEAA690NonMembersResponse() {

        List<MemberDetails> nonMemberDetails = HtmlResponseParser.parseEAA690NonMembersResponse(
                eaa690NonMemberListResponseStr);

        Assert.assertNotNull("Non-members details list was null", nonMemberDetails);
        Assert.assertTrue("Non-members details list has no info", nonMemberDetails.size() > 0);
    }

    /**
     * Test parsing of EAA690 MemberDetails response
     */
    @Test
    public void testParseEAA690MemberDetailsInvalidDateResponse() {

        MemberDetails memberDetails = HtmlResponseParser.parseEAA690MemberDetailsResponse(
                eaa690MemberDetailsInvalidDateResponseStr);

        Assert.assertNotNull("MemberDetails is null", memberDetails);
        Assert.assertNotNull("MemberDetails.addressLine1 is null", memberDetails.getAddressLine1());
        Assert.assertNotNull("MemberDetails.city is null", memberDetails.getCity());
        Assert.assertNotNull("MemberDetails.state is null", memberDetails.getState());
        Assert.assertNotNull("MemberDetails.zipCode is null", memberDetails.getZipCode());
        Assert.assertNotNull("MemberDetails.homePhone is null", memberDetails.getHomePhone());
        Assert.assertNotNull("MemberDetails.cellPhone is null", memberDetails.getCellPhone());
        Assert.assertNotNull("MemberDetails.email is null", memberDetails.getEmail());
        Assert.assertNotNull("MemberDetails.spouseName is null", memberDetails.getSpouseName());
        Assert.assertNotNull("MemberDetails.eaaNumber is null", memberDetails.getEaaNumber());
        Assert.assertNotNull("MemberDetails.tailNumber is null", memberDetails.getTailNumber());
        Assert.assertTrue("MemberDetails.member is false", memberDetails.isChapterMember());
        Assert.assertTrue("MemberDetails.paid is false", memberDetails.isChapterPaid());
        Assert.assertNull("MemberDetails.datePaid is not null", memberDetails.getChapterDatePaid());
        Assert.assertNotNull("MemberDetails.membershipType is null", memberDetails.getChapterMembershipType());
    }

    /**
     * Test parsing of EAA690 MemberDetails response
     */
    @Test
    public void testParseEAA690MemberDetailsNullResponse() {

        MemberDetails memberDetails = HtmlResponseParser.parseEAA690MemberDetailsResponse(null);

        Assert.assertNotNull("MemberDetails is null", memberDetails);
        Assert.assertNull("MemberDetails.addressLine1 is not null", memberDetails.getAddressLine1());
        Assert.assertNull("MemberDetails.city is not null", memberDetails.getCity());
        Assert.assertNull("MemberDetails.state is not null", memberDetails.getState());
        Assert.assertNull("MemberDetails.zipCode is not null", memberDetails.getZipCode());
        Assert.assertNull("MemberDetails.homePhone is not null", memberDetails.getHomePhone());
        Assert.assertNull("MemberDetails.cellPhone is not null", memberDetails.getCellPhone());
        Assert.assertNull("MemberDetails.email is not null", memberDetails.getEmail());
        Assert.assertNull("MemberDetails.spouseName is not null", memberDetails.getSpouseName());
        Assert.assertNull("MemberDetails.eaaNumber is not null", memberDetails.getEaaNumber());
        Assert.assertNull("MemberDetails.tailNumber is not null", memberDetails.getTailNumber());
        Assert.assertFalse("MemberDetails.member is true", memberDetails.isChapterMember());
        Assert.assertFalse("MemberDetails.paid is true", memberDetails.isChapterPaid());
        Assert.assertNull("MemberDetails.datePaid is not null", memberDetails.getChapterDatePaid());
        Assert.assertNull("MemberDetails.membershipType is not null", memberDetails.getChapterMembershipType());
    }

    /**
     * Test parsing of EAA690 MemberDetails response
     */
    @Test
    public void testParseEAA690MemberDetailsInvalidResponse() {

        MemberDetails memberDetails = HtmlResponseParser.parseEAA690MemberDetailsResponse(
                eaa690MemberDetailsInvalidResponseStr);

        Assert.assertNotNull("MemberDetails is null", memberDetails);
        Assert.assertNotNull("MemberDetails.addressLine1 is null", memberDetails.getAddressLine1());
        Assert.assertNotNull("MemberDetails.city is null", memberDetails.getCity());
        Assert.assertNotNull("MemberDetails.state is null", memberDetails.getState());
        Assert.assertNotNull("MemberDetails.zipCode is null", memberDetails.getZipCode());
        Assert.assertNull("MemberDetails.homePhone is not null", memberDetails.getHomePhone());
        Assert.assertNull("MemberDetails.cellPhone is not null", memberDetails.getCellPhone());
        Assert.assertNull("MemberDetails.email is not null", memberDetails.getEmail());
        Assert.assertNull("MemberDetails.spouseName is not null", memberDetails.getSpouseName());
        Assert.assertNull("MemberDetails.eaaNumber is not null", memberDetails.getEaaNumber());
        Assert.assertNull("MemberDetails.tailNumber is not null", memberDetails.getTailNumber());
        Assert.assertFalse("MemberDetails.member is true", memberDetails.isChapterMember());
        Assert.assertFalse("MemberDetails.paid is true", memberDetails.isChapterPaid());
        Assert.assertNull("MemberDetails.datePaid is not null", memberDetails.getChapterDatePaid());
        Assert.assertNull("MemberDetails.membershipType is not null", memberDetails.getChapterMembershipType());
    }

    /**
     * Reads in fileName from src/test/resources
     *
     * @param fileName to be read in
     * @return file contents
     */
    private static String readResponseHtml(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
        } catch (IOException ioe) {
            LOGGER.error(String.format("Unable to read response file [%s]", fileName), ioe);
        }
        return stringBuilder.toString();
    }
}
