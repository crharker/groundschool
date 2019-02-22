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
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.UserService;

/**
 * QuizValidator
 *
 * @author brianmichael
 */
@Component
public class QuizValidator {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizValidator.class);

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Quiz Validation
     *
     * @param quiz Quiz
     * @throws InvalidPayloadException when quiz information is invalid
     */
    public void validate(Quiz quiz) throws InvalidPayloadException {
        empty(quiz);
    }

    /**
     * Validates access to an quiz by the principal user
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
     * Ensures quiz object is not null
     *
     * @param quiz Quiz
     * @throws InvalidPayloadException when quiz is null
     */
    private static void empty(Quiz quiz) throws InvalidPayloadException {
        if (quiz == null) {
            String msg = "No quiz information was provided";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
    }

}
