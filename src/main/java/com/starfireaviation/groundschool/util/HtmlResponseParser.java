/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starfireaviation.groundschool.model.ChapterMembershipType;
import com.starfireaviation.groundschool.model.MemberDetails;

/**
 * HtmlResponseParser
 *
 * @author brianmichael
 */
public class HtmlResponseParser {

    /**
     * PAGE_NUMBER_PATTERN
     */
    private static final Pattern PAGE_NUMBER_PATTERN = Pattern.compile("\\d\\s*of\\s*(\\d*)", Pattern.DOTALL);

    /**
     * NAME_PATTERN
     */
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "<legend>Details - (.*?) (.*?) .*</legend>",
            Pattern.DOTALL);

    /**
     * EAA_MEMBER_PATTERN
     */
    private static final Pattern EAA_MEMBER_PATTERN = Pattern.compile(
            "<span id=\\\"ContentPlaceHolder1_expdate\\\">This member's expiration date is: (.*?)</span>",
            Pattern.DOTALL);

    /**
     * MEMBER_LIST_PATTERN
     */
    private static final Pattern MEMBER_LIST_PATTERN = Pattern.compile(
            "<tr>.*?<td>(.*?)</td>.*?<td>.*?<a href=\\\"/Member/Edit/(.*?)\\\">.*?</td>.*?<td>(.*?)</td>.*?<td>.*?<a href=\\\"/Member/Edit/(.*?)\\\">.*?</td>",
            Pattern.DOTALL);

    /**
     * NON_MEMBER_LIST_PATTERN
     */
    private static final Pattern NON_MEMBER_LIST_PATTERN = Pattern.compile(
            "<tr>.*?<td>(.*?)</td>.*?<td>.*?<a href=\\\"/NonMember/Edit/(.*?)\\\">.*?</td>.*?<td>(.*?)</td>.*?<td>.*?<a href=\\\"/NonMember/Edit/(.*?)\\\">.*?</td>",
            Pattern.DOTALL);

    /**
     * SimpleDateFormat - Date format is: 2/28/2019
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlResponseParser.class);

    /**
     * TABLE_DATA
     */
    private static final String TABLE_DATA = "<td>";

    /**
     * CLOSE_TABLE_DATA
     */
    private static final String CLOSE_TABLE_DATA = "</td>";

    /**
     * Parses HTML response from call to https://www.eaa.org/apps/chapters/chapterpersonlookup.aspx
     *
     * @param response from call to https://www.eaa.org/apps/chapters/chapterpersonlookup.aspx
     * @return MemberDetails
     */
    public static MemberDetails parseEAAMemberInfo(String response) {
        MemberDetails memberDetails = new MemberDetails();
        final Matcher matcher = EAA_MEMBER_PATTERN.matcher(response);
        if (matcher.find()) {
            final String dateStr = matcher.group(1);
            LOGGER.info(String.format("parseEAAMemberInfo() found [%s]", dateStr));
            try {
                final Date expirationDate = SIMPLE_DATE_FORMAT.parse(dateStr);
                memberDetails.setEaaMemberExpiryDate(expirationDate);
                memberDetails.setEaaPaid(new Date().before(expirationDate));
            } catch (ParseException e) {
                LOGGER.warn(
                        String.format(
                                "parseEAAMemberInfo() Unable to parse date [%s].  Msg: %s",
                                dateStr,
                                e.getMessage()));
            }
        }
        return memberDetails;
    }

    /**
     * Parses HTML response from call to https://www.eaa.org/apps/chapters/chapterpersonlookup.aspx
     *
     * @param response from call to https://www.eaa.org/apps/chapters/chapterpersonlookup.aspx
     * @return MemberDetails
     */
    public static MemberDetails parseEAAMemberTraining(String response) {
        //MemberDetails memberDetails = new MemberDetails();
        //final Matcher matcher = EAA_MEMBER_PATTERN.matcher(response);
        //if (matcher.find()) {
        //    try {
        //        memberDetails.setEaaMemberExpiryDate(sdf.parse(matcher.group(1)));
        //        memberDetails.setEaaPaid(true);
        //    } catch (ParseException e) {
        //        LOGGER.warn(String.format("Unable to parse date [%s].  Msg: %s", matcher.group(1), e.getMessage()));
        //    }
        //}
        return null;
    }

    /**
     * Parses HTML response from call to http://eaa690.net/Member/Details/<ID>
     *
     * @param response HTML response string
     * @return MemberDetails
     */
    public static MemberDetails parseEAA690MemberDetailsResponse(String response) {
        final MemberDetails memberDetails = new MemberDetails();
        if (response == null) {
            return memberDetails;
        }
        final Matcher nameMatcher = NAME_PATTERN.matcher(response);
        if (nameMatcher.find()) {
            memberDetails.setFirstName(nameMatcher.group(1));
            memberDetails.setLastName(nameMatcher.group(2));
        }
        final String fieldset = response.substring(
                response.indexOf("<fieldset>"),
                response.lastIndexOf("</fieldset>") + 11);
        final String table1 = fieldset.substring(
                fieldset.indexOf("<table style=\"width: 100%;\">"),
                fieldset.indexOf("</table>") + 8);
        final List<String> table1Rows = parseTableRows(table1);
        memberDetails.setAddressLine1(getAddressValue("<th>Address Line 1</th>", table1Rows));
        memberDetails.setCity(getAddressValue("<th>City</th>", table1Rows));
        memberDetails.setState(getAddressValue("<th>State</th>", table1Rows));
        memberDetails.setZipCode(getAddressValue("<th>Zip Code</th>", table1Rows));
        final List<String> phoneValues = getPhoneValues(table1Rows);
        if (phoneValues != null && phoneValues.size() == 2) {
            memberDetails.setHomePhone(getPhoneValue(phoneValues.get(0)));
            memberDetails.setCellPhone(getPhoneValue(phoneValues.get(1)));
        }
        memberDetails.setEmail(getEmailValue(table1Rows));
        memberDetails.setSpouseName(getSpouseValue(table1Rows));
        final List<String> eaaTailValues = getEAATailValues(table1Rows);
        if (eaaTailValues != null && eaaTailValues.size() == 2) {
            memberDetails.setEaaNumber(eaaTailValues.get(0));
            memberDetails.setTailNumber(eaaTailValues.get(1));
        }
        final List<String> memberPaidDateList = getMemberPaidDateValues(table1Rows);
        if (memberPaidDateList != null && memberPaidDateList.size() == 3) {
            memberDetails.setChapterMember(isCheckBoxSelected(memberPaidDateList.get(0)));
            memberDetails.setChapterPaid(isCheckBoxSelected(memberPaidDateList.get(1)));
            try {
                memberDetails.setChapterDatePaid(SIMPLE_DATE_FORMAT.parse(memberPaidDateList.get(2)));
            } catch (ParseException e) {
                LOGGER.warn("Unable to parse chapter date paid.  " + e.getMessage());
            }
        }
        memberDetails.setChapterMembershipType(getMembershipTypeValue(table1Rows));
        return memberDetails;
    }

    /**
     * Cleans up phone number for storage in DB
     *
     * @param toBeParsed string to be cleaned
     * @return cleaned string
     */
    private static String getPhoneValue(String toBeParsed) {
        if (toBeParsed == null) {
            return toBeParsed;
        }
        String response = toBeParsed
                .replaceAll("-", "")
                .replaceAll("\\)", "")
                .replaceAll("\\(", "")
                .replaceAll(" ", "");
        if (response.length() > 10) {
            response = response.substring(0, 10);
        }
        return response;
    }

    /**
     * Parses HTML response for max page number value
     *
     * @param response HTML response string
     * @return max page number
     */
    public static int parseMaxPageNumberFromEAA690NetResponse(String response) {
        int maxPageNumber = 1;
        Matcher matcher = PAGE_NUMBER_PATTERN.matcher(response);
        if (matcher.find()) {
            maxPageNumber = Integer.parseInt(matcher.group(1));
        }
        return maxPageNumber;
    }

    /**
     * Parses HTML response to obtain all non-member entries
     *
     * @param response HTML response string
     * @return list of MemberDetails
     */
    public static List<MemberDetails> parseEAA690NonMembersResponse(final String response) {
        //LOGGER.info(String.format("parseEAA690NonMembersResponse() parsing response [%s]", response));
        final List<MemberDetails> memberDetailsList = new ArrayList<>();
        try {
            final String fieldset = response.substring(
                    response.indexOf("<fieldset>"),
                    response.lastIndexOf("</fieldset>") + 11);
            LOGGER.info(String.format("parseEAA690NonMembersResponse() parsing fieldset [%s]", fieldset));
            int fieldsetIndex = 0;
            while (fieldsetIndex < fieldset.length()) {
                fieldsetIndex = fieldset.indexOf("<tr>", fieldsetIndex);
                int endIndex = fieldset.length();
                endIndex = fieldset.indexOf("<tr>", fieldsetIndex + 1);
                if (endIndex < 0) {
                    endIndex = fieldset.length();
                }
                final String tableRow = fieldset.substring(
                        fieldset.indexOf("<tr>", fieldsetIndex),
                        endIndex);
                final Matcher matcher = NON_MEMBER_LIST_PATTERN.matcher(tableRow);
                if (matcher.find()) {
                    final String[] name1 = matcher.group(1).trim().split(" ");
                    memberDetailsList.add(
                            new MemberDetails(Long.valueOf(matcher.group(2).trim()), name1[0], name1[1], false));
                    final String[] name2 = matcher.group(3).trim().split(" ");
                    memberDetailsList.add(
                            new MemberDetails(Long.valueOf(matcher.group(4).trim()), name2[0], name2[1], false));
                }
                fieldsetIndex = endIndex;
            }
        } catch (IndexOutOfBoundsException ioobe) {
            LOGGER.error("Error parsing EAA690 non-members response", ioobe);
        }
        return memberDetailsList;
    }

    /**
     * Parses HTML response to obtain all member entries
     *
     * @param response HTML response string
     * @return list of MemberDetails
     */
    public static List<MemberDetails> parseEAA690MembersResponse(String response) {
        //LOGGER.info(String.format("parseEAA690MembersResponse() parsing response [%s]", response));
        final List<MemberDetails> memberDetailsList = new ArrayList<>();
        try {
            final String fieldset = response.substring(
                    response.indexOf("<fieldset>"),
                    response.lastIndexOf("</fieldset>") + 11);
            LOGGER.info(String.format("parseEAA690MembersResponse() parsing fieldset [%s]", fieldset));
            int fieldsetIndex = 0;
            while (fieldsetIndex < fieldset.length()) {
                fieldsetIndex = fieldset.indexOf("<tr>", fieldsetIndex);
                int endIndex = fieldset.length();
                endIndex = fieldset.indexOf("<tr>", fieldsetIndex + 1);
                if (endIndex < 0) {
                    endIndex = fieldset.length();
                }
                final String tableRow = fieldset.substring(
                        fieldset.indexOf("<tr>", fieldsetIndex),
                        endIndex);
                final Matcher matcher = MEMBER_LIST_PATTERN.matcher(tableRow);
                if (matcher.find()) {
                    final String[] name1 = matcher.group(1).trim().split(" ");
                    memberDetailsList.add(
                            new MemberDetails(Long.valueOf(matcher.group(2).trim()), name1[0], name1[1], false));
                    final String[] name2 = matcher.group(3).trim().split(" ");
                    memberDetailsList.add(
                            new MemberDetails(Long.valueOf(matcher.group(4).trim()), name2[0], name2[1], false));
                }
                fieldsetIndex = endIndex;
            }
        } catch (IndexOutOfBoundsException ioobe) {
            LOGGER.error("Error parsing EAA690 members response", ioobe);
        }
        return memberDetailsList;
    }

    /**
     * Derives if a checkbox is checked or not
     *
     * @param checkbox string
     * @return true is checkbox is checked
     */
    private static boolean isCheckBoxSelected(String checkbox) {
        boolean checked = false;
        if (checkbox != null && checkbox.contains("checked=\"checked\"")) {
            checked = true;
        }
        return checked;
    }

    /**
     * Gets address value
     *
     * @param key entry key
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static String getAddressValue(String key, List<String> tableRows) {
        String value = null;
        for (final String tr : tableRows) {
            if (tr != null && tr.contains(key)) {
                value = tr.substring(tr.indexOf(TABLE_DATA) + TABLE_DATA.length(), tr.indexOf(CLOSE_TABLE_DATA));
                break;
            }
        }
        return value;
    }

    /**
     * Gets phone values
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static List<String> getPhoneValues(List<String> tableRows) {
        final List<String> values = new ArrayList<>();
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Home Phone</th>")) {
                try {
                    final String tr2 = tableRows.get(i + 1);
                    values.add(tr2.substring(tr2.indexOf(TABLE_DATA) + 4, tr2.indexOf(CLOSE_TABLE_DATA)));
                    values.add(
                            tr2.substring(
                                    tr2.indexOf(TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA)) + TABLE_DATA.length(),
                                    tr2.indexOf(CLOSE_TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA) + 1)));
                    break;
                } catch (StringIndexOutOfBoundsException sioobe) {
                    LOGGER.warn("Unable to parse home phone number.  " + sioobe.getMessage());
                }
            }
        }
        return values;
    }

    /**
     * Gets Member/Paid/Date Membership Paid values
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static List<String> getMemberPaidDateValues(List<String> tableRows) {
        final List<String> values = new ArrayList<>();
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Date Membership Paid</th>")) {
                final String tr2 = tableRows.get(i + 1);
                values.add(tr2.substring(tr2.indexOf(TABLE_DATA) + TABLE_DATA.length(), tr2.indexOf(CLOSE_TABLE_DATA)));
                values.add(
                        tr2.substring(
                                tr2.indexOf(TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA)) + TABLE_DATA.length(),
                                tr2.indexOf(CLOSE_TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA) + 1)));
                values.add(
                        tr2.substring(
                                tr2.indexOf(TABLE_DATA, tr2.lastIndexOf("checkbox")) + TABLE_DATA.length(),
                                tr2.lastIndexOf(CLOSE_TABLE_DATA)));
                break;
            }
        }
        return values;
    }

    /**
     * Gets EAA & Tail Number values
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static List<String> getEAATailValues(List<String> tableRows) {
        final List<String> values = new ArrayList<>();
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Tail Number</th>")) {
                try {
                    final String tr2 = tableRows.get(i + 1);
                    values.add(
                            tr2.substring(
                                    tr2.indexOf(TABLE_DATA) + TABLE_DATA.length(),
                                    tr2.indexOf(CLOSE_TABLE_DATA)));
                    values.add(
                            tr2.substring(
                                    tr2.indexOf(TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA)) + TABLE_DATA.length(),
                                    tr2.indexOf(CLOSE_TABLE_DATA, tr2.indexOf(CLOSE_TABLE_DATA) + 1)));
                    break;
                } catch (StringIndexOutOfBoundsException sioobe) {
                    LOGGER.warn("Unable to parse tail number.  " + sioobe.getMessage());
                }
            }
        }
        return values;
    }

    /**
     * Gets email value
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static String getEmailValue(List<String> tableRows) {
        String value = null;
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Member Email</th>")) {
                try {
                    final String tr2 = tableRows.get(i + 1);
                    final String aHref = tr2.substring(tr2.indexOf(TABLE_DATA) + 4, tr2.indexOf(CLOSE_TABLE_DATA));
                    value = aHref.substring(aHref.indexOf('>') + 1, aHref.indexOf("</a>"));
                    break;
                } catch (StringIndexOutOfBoundsException sioobe) {
                    LOGGER.warn("Unable to parse email.  " + sioobe.getMessage());
                }
            }
        }
        return value;
    }

    /**
     * Gets spouse value
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static String getSpouseValue(List<String> tableRows) {
        String value = null;
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Spouse Name (First Name Only)</th>")) {
                final String tr2 = tableRows.get(i + 1);
                value = tr2.substring(tr2.indexOf(TABLE_DATA) + TABLE_DATA.length(), tr2.indexOf(CLOSE_TABLE_DATA));
                break;
            }
        }
        return value;
    }

    /**
     * Gets membership type value
     *
     * @param tableRows entries from table 1 of HTML response
     * @return retrieved value
     */
    private static ChapterMembershipType getMembershipTypeValue(List<String> tableRows) {
        ChapterMembershipType type = null;
        for (int i = 0; i < tableRows.size(); i++) {
            final String tr = tableRows.get(i);
            if (tr != null && tr.contains("<th>Membership Type</th>")) {
                try {
                    final String tr2 = tableRows.get(i + 1);
                    String value = tr2.substring(
                            tr2.indexOf(TABLE_DATA) + TABLE_DATA.length(),
                            tr2.indexOf(CLOSE_TABLE_DATA));
                    if (value.contains("STUDENT")) {
                        type = ChapterMembershipType.STUDENT;
                    } else {
                        type = ChapterMembershipType.valueOf(value.toUpperCase());
                    }
                    break;
                } catch (IllegalArgumentException iae) {
                    LOGGER.warn("Unable to parse chapter membership type.  " + iae.getMessage());
                }
            }
        }
        return type;
    }

    /**
     * Parse out each
     * <tr>
     * ...
     * </tr>
     * found in HTML table
     *
     * @param table table found in HTML response
     * @return List of TR entries
     */
    private static List<String> parseTableRows(String table) {
        final List<String> trList = new ArrayList<>();
        int index = 0;
        final int lastIndex = table.lastIndexOf("</tr>") + 5;
        while (index < lastIndex) {
            final int endIndex = table.indexOf("</tr>", index) + 5;
            trList.add(table.substring(table.indexOf("<tr>", index), endIndex));
            index = endIndex;
        }
        return trList;
    }

}
