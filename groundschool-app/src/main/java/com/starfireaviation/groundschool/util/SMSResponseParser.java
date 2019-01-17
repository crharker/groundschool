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
     * A_PATTERN
     */
    public static final Pattern A_PATTERN = Pattern.compile("A", Pattern.CASE_INSENSITIVE);

    /**
     * B_PATTERN
     */
    public static final Pattern B_PATTERN = Pattern.compile("B", Pattern.CASE_INSENSITIVE);

    /**
     * C_PATTERN
     */
    public static final Pattern C_PATTERN = Pattern.compile("C", Pattern.CASE_INSENSITIVE);

    /**
     * D_PATTERN
     */
    public static final Pattern D_PATTERN = Pattern.compile("D", Pattern.CASE_INSENSITIVE);

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
        } else if (isAResponse(message)) {
            smsResponseOption = SMSResponseOption.A;
        } else if (isBResponse(message)) {
            smsResponseOption = SMSResponseOption.B;
        } else if (isCResponse(message)) {
            smsResponseOption = SMSResponseOption.C;
        } else if (isDResponse(message)) {
            smsResponseOption = SMSResponseOption.D;
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

    /**
     * Evaluates user's message to see if it is a A message
     *
     * @param message to be evaluated
     * @return if a A message
     */
    private static boolean isAResponse(String message) {
        boolean isA = false;
        Matcher matcher = A_PATTERN.matcher(message);
        if (matcher.find()) {
            isA = true;
        }
        return isA;
    }

    /**
     * Evaluates user's message to see if it is a B message
     *
     * @param message to be evaluated
     * @return if a B message
     */
    private static boolean isBResponse(String message) {
        boolean isB = false;
        Matcher matcher = B_PATTERN.matcher(message);
        if (matcher.find()) {
            isB = true;
        }
        return isB;
    }

    /**
     * Evaluates user's message to see if it is a C message
     *
     * @param message to be evaluated
     * @return if a C message
     */
    private static boolean isCResponse(String message) {
        boolean isC = false;
        Matcher matcher = C_PATTERN.matcher(message);
        if (matcher.find()) {
            isC = true;
        }
        return isC;
    }

    /**
     * Evaluates user's message to see if it is a D message
     *
     * @param message to be evaluated
     * @return if a D message
     */
    private static boolean isDResponse(String message) {
        boolean isD = false;
        Matcher matcher = D_PATTERN.matcher(message);
        if (matcher.find()) {
            isD = true;
        }
        return isD;
    }

}
