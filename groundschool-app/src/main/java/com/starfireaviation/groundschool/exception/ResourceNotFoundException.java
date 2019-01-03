/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException
 *
 * @author brianmichael
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Initializes an instance of <code>ResourceNotFoundException</code> with the default data.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Initializes an instance of <code>ResourceNotFoundException</code> with the default data.
     *
     * @param message message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Initializes an instance of <code>ResourceNotFoundException</code> with the default data.
     *
     * @param message message
     * @param cause cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
