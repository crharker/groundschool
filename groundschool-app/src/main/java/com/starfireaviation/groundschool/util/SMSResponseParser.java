/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.starfireaviation.groundschool.model.SMSResponseOption;

/**
 * SMSResponseParser
 *
 * @author brianmichael
 */
public class SMSResponseParser {

    /**
     * STOP_PATTERN
     */
    public static final Pattern STOP_PATTERN = Pattern.compile(".*?(STOP).*?", Pattern.CASE_INSENSITIVE);

    /**
     * CONFIRM_PATTERN
     */
    public static final Pattern CONFIRM_PATTERN = Pattern.compile(".*?(CONFIRM).*?", Pattern.CASE_INSENSITIVE);

    /**
     * DECLINE_PATTERN
     */
    public static final Pattern DECLINE_PATTERN = Pattern.compile(".*?(DECLINE).*?", Pattern.CASE_INSENSITIVE);

    /**
     * Determines user's response
     *
     * @param message to be evaluated
     * @return SMSResponseOption
     */
    public static SMSResponseOption determineResponse(String message) {
        SMSResponseOption smsResponseOption = SMSResponseOption.UNKNOWN;
        if (isStopResponse(message)) {
            smsResponseOption = SMSResponseOption.STOP;
        } else if (isDeclineResponse(message)) {
            smsResponseOption = SMSResponseOption.DECLINE;
        } else if (isConfirmResponse(message)) {
            smsResponseOption = SMSResponseOption.CONFIRM;
        }
        return smsResponseOption;
    }

    /**
     * Evaluates user's message to see if it is a STOP message
     *
     * @param message to be evaluated
     * @return if a STOP message
     */
    private static boolean isStopResponse(String message) {
        boolean isStop = false;
        Matcher matcher = STOP_PATTERN.matcher(message);
        if (matcher.find()) {
            isStop = true;
        }
        return isStop;
    }

    /**
     * Evaluates user's message to see if it is a CONFIRM message
     *
     * @param message to be evaluated
     * @return if a CONFIRM message
     */
    private static boolean isConfirmResponse(String message) {
        boolean isConfirm = false;
        Matcher matcher = CONFIRM_PATTERN.matcher(message);
        if (matcher.find()) {
            isConfirm = true;
        }
        return isConfirm;
    }

    /**
     * Evaluates user's message to see if it is a DECLINE message
     *
     * @param message to be evaluated
     * @return if a DECLINE message
     */
    private static boolean isDeclineResponse(String message) {
        boolean isDecline = false;
        Matcher matcher = DECLINE_PATTERN.matcher(message);
        if (matcher.find()) {
            isDecline = true;
        }
        return isDecline;
    }

}
