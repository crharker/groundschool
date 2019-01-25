/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AccessDeniedException
 *
 * @author brianmichael
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends Exception {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Initializes an instance of <code>AccessDeniedException</code> with the default data.
     */
    public AccessDeniedException() {
        super();
    }

    /**
     * Initializes an instance of <code>AccessDeniedException</code> with the default data.
     *
     * @param message message
     */
    public AccessDeniedException(String message) {
        super(message);
    }

    /**
     * Initializes an instance of <code>AccessDeniedException</code> with the default data.
     *
     * @param message message
     * @param cause cause
     */
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
