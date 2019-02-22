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
import com.starfireaviation.groundschool.exception.ConflictException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.UserEntity;
import com.starfireaviation.groundschool.repository.UserRepository;
import com.starfireaviation.groundschool.service.UserService;

/**
 * UserValidator
 *
 * @author brianmichael
 */
@Component
public class UserValidator {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    /**
     * UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * User Validation
     *
     * @param user User
     * @throws InvalidPayloadException when a username conflict occurs
     * @throws ConflictException when user data conflict with another user
     */
    public void validate(User user) throws ConflictException, InvalidPayloadException {
        empty(user);
        conflict(user);
    }

    /**
     * Validates access to a user's information by the principal user
     *
     * @param userId User ID
     * @param principal Principal
     * @throws ResourceNotFoundException when principal user is not found
     * @throws AccessDeniedException when principal user is not permitted to access user info
     */
    public void access(long userId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        final User loggedInUser = userService.findByUsername(principal.getName());
        final Role role = loggedInUser.getRole();
        if (role != Role.ADMIN && role != Role.INSTRUCTOR && loggedInUser.getId() != userId) {
            throw new AccessDeniedException("Current user is not authorized to retrieve this user's information");
        }
    }

    /**
     * Ensures user object is not null
     *
     * @param user User
     * @throws InvalidPayloadException when user is null
     */
    private static void empty(User user) throws InvalidPayloadException {
        if (user == null) {
            String msg = "No user information was provided";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
    }

    /**
     * Ensures provided user information does not conflict with another user's information
     *
     * @param user User
     * @throws ConflictException when user data conflict with another user
     * @throws InvalidPayloadException when user is null
     */
    private void conflict(User user) throws ConflictException, InvalidPayloadException {
        username(user);
        sms(user);
        slack(user);
        email(user);
        eaaNumber(user);
    }

    /**
     * Ensure username is unique
     *
     * @param user User
     * @throws InvalidPayloadException when no username is provided
     * @throws ConflictException when username conflicts with another user's username
     */
    private void username(User user) throws InvalidPayloadException, ConflictException {
        if (user.getUsername() == null) {
            String msg = "Username is a required value";
            LOGGER.warn(msg);
            throw new InvalidPayloadException(msg);
        }
        UserEntity existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getId() != user.getId()) {
            String msg = String.format(
                    "Another user ["
                            + existingUser.getFirstName()
                            + " "
                            + existingUser.getLastName()
                            + "] has already taken username [%s]",
                    user.getUsername());
            LOGGER.warn(msg);
            throw new ConflictException(msg);
        }
    }

    /**
     * Ensure sms is unique
     *
     * @param user User
     * @throws ConflictException when sms conflicts with another user's sms
     */
    private void sms(User user) throws ConflictException {
        if (user.getSms() != null) {
            UserEntity existingUser = userRepository.findBySms(user.getSms());
            if (existingUser != null && existingUser.getId() != user.getId()) {
                String msg = String.format(
                        "Another user ["
                                + existingUser.getFirstName()
                                + " "
                                + existingUser.getLastName()
                                + "] has already taken number [%s]",
                        user.getSms());
                LOGGER.warn(msg);
                throw new ConflictException(msg);
            }
        }
    }

    /**
     * Ensure slack is unique
     *
     * @param user User
     * @throws ConflictException when slack conflicts with another user's slack
     */
    private void slack(User user) throws ConflictException {
        if (user.getSlack() != null) {
            UserEntity existingUser = userRepository.findBySlack(user.getSlack());
            if (existingUser != null && existingUser.getId() != user.getId()) {
                String msg = String.format(
                        "Another user ["
                                + existingUser.getFirstName()
                                + " "
                                + existingUser.getLastName()
                                + "] has already taken slack name [%s]",
                        user.getSlack());
                LOGGER.warn(msg);
                throw new ConflictException(msg);
            }
        }
    }

    /**
     * Ensure email is unique
     *
     * @param user User
     * @throws ConflictException when email conflicts with another user's email
     */
    private void email(User user) throws ConflictException {
        if (user.getEmail() != null) {
            UserEntity existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null && existingUser.getId() != user.getId()) {
                String msg = String.format(
                        "Another user ["
                                + existingUser.getFirstName()
                                + " "
                                + existingUser.getLastName()
                                + "] has already taken email address [%s]",
                        user.getEmail());
                LOGGER.warn(msg);
                throw new ConflictException(msg);
            }
        }
    }

    /**
     * Ensure EAA number is unique
     *
     * @param user User
     * @throws ConflictException when EAA number conflicts with another user's EAA number
     */
    private void eaaNumber(User user) throws ConflictException {
        if (user.getEaaNumber() != null) {
            UserEntity existingUser = userRepository.findByEaaNumber(user.getEaaNumber());
            if (existingUser != null && existingUser.getId() != user.getId()) {
                String msg = String.format(
                        "Another user ["
                                + existingUser.getFirstName()
                                + " "
                                + existingUser.getLastName()
                                + "] has already taken EAA Number [%s]",
                        user.getEaaNumber());
                LOGGER.warn(msg);
                throw new ConflictException(msg);
            }
        }
    }
}
