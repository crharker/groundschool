/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.validation;

import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.util.ResponseParser;

/**
 * ResponseValidator
 *
 * @author brianmichael
 */
public class ResponseValidator {

    /**
     * Response Validation
     *
     * @param message String
     * @throws InvalidPayloadException when response is not valid
     */
    public static void validate(String message) throws InvalidPayloadException {
        switch (ResponseParser.determineResponse(message)) {
            case A:
            case B:
            case C:
            case D:
            case CONFIRM:
            case DECLINE:
            case SKIP:
            case STOP:
                return;
            default:
                throw new InvalidPayloadException();
        }
    }

}
