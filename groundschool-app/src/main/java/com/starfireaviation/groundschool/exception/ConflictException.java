/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ConflictException
 *
 * @author brianmichael
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Initializes an instance of <code>ConflictException</code> with the default data.
     */
    public ConflictException() {
        super();
    }

    /**
     * Initializes an instance of <code>ConflictException</code> with the default data.
     *
     * @param message message
     */
    public ConflictException(String message) {
        super(message);
    }

    /**
     * Initializes an instance of <code>ConflictException</code> with the default data.
     *
     * @param message message
     * @param cause cause
     */
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
