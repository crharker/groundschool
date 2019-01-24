/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidPayloadException
 *
 * @author brianmichael
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPayloadException extends Exception {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Initializes an instance of <code>InvalidPayloadException</code> with the default data.
     */
    public InvalidPayloadException() {
        super();
    }

    /**
     * Initializes an instance of <code>InvalidPayloadException</code> with the default data.
     *
     * @param message message
     */
    public InvalidPayloadException(String message) {
        super(message);
    }

    /**
     * Initializes an instance of <code>InvalidPayloadException</code> with the default data.
     *
     * @param message message
     * @param cause cause
     */
    public InvalidPayloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
