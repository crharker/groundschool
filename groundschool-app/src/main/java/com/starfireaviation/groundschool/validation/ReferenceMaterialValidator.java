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
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.UserService;

/**
 * ReferenceMaterialValidator
 *
 * @author brianmichael
 */
@Component
public class ReferenceMaterialValidator {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceMaterialValidator.class);

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * ReferenceMaterial Validation
     *
     * @param referenceMaterial ReferenceMaterial
     * @throws InvalidPayloadException when reference material information is invalid
     */
    public void validate(ReferenceMaterial referenceMaterial) throws InvalidPayloadException {
        empty(referenceMaterial);
    }

    /**
     * Validates access to an reference material by the principal user
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
     * Ensures reference material object is not null
     *
     * @param referenceMaterial ReferenceMaterial
     * @throws InvalidPayloadException when referenceMaterial is null
     */
    private static void empty(ReferenceMaterial referenceMaterial) throws InvalidPayloadException {
        if (referenceMaterial == null) {
            String msg = "No reference material information was provided";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
    }

}
