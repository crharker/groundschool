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
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.UserService;

/**
 * LessonPlanValidator
 *
 * @author brianmichael
 */
@Component
public class LessonPlanValidator {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonPlanValidator.class);

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * LessonPlan Validation
     *
     * @param lessonPlan LessonPlan
     * @throws InvalidPayloadException when lesson plan information is invalid
     */
    public void validate(LessonPlan lessonPlan) throws InvalidPayloadException {
        empty(lessonPlan);
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
     * Ensures lesson plan object is not null
     *
     * @param lessonPlan LessonPlan
     * @throws InvalidPayloadException when lesson plan is null
     */
    private static void empty(LessonPlan lessonPlan) throws InvalidPayloadException {
        if (lessonPlan == null) {
            String msg = "No lesson plan information was provided";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
    }

}