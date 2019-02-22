/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.validation;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starfireaviation.groundschool.exception.AccessDeniedException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.UserService;

/**
 * EventValidator
 *
 * @author brianmichael
 */
@Component
public class EventValidator {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventValidator.class);

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Event Validation
     *
     * @param event Event
     * @throws InvalidPayloadException when a username conflict occurs
     */
    public void validate(Event event) throws InvalidPayloadException {
        empty(event);
    }

    /**
     * Validates access to an event by the principal user
     *
     * @param principal Principal
     * @throws ResourceNotFoundException when principal user is not found
     * @throws AccessDeniedException when principal user is not permitted to access user info
     */
    public void access(Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        final User loggedInUser = userService.findByUsername(principal.getName());
        final Role role = loggedInUser.getRole();
        if (role != Role.ADMIN && role != Role.INSTRUCTOR) {
            throw new AccessDeniedException("Current user is not authorized");
        }
    }

    /**
     * Ensures event object is not null
     *
     * @param event Event
     * @throws InvalidPayloadException when user is null
     */
    private static void empty(Event event) throws InvalidPayloadException {
        if (event == null) {
            String msg = "No event information was provided";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
    }

}
