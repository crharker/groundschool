/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.exception.AccessDeniedException;
import com.starfireaviation.groundschool.exception.ConflictException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.CodeGenerator;
import com.starfireaviation.groundschool.validation.UserValidator;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/users"
})
public class UserController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * UserValidator
     */
    @Autowired
    private UserValidator userValidator;

    /**
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * BCryptPasswordEncoder
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Creates a user
     *
     * @param user User
     * @return User
     * @throws InvalidPayloadException when invalid data is provided
     * @throws ResourceNotFoundException when no user is found
     * @throws ConflictException when user data conflict with another user
     */
    @PostMapping
    public User post(@RequestBody User user) throws InvalidPayloadException, ResourceNotFoundException,
            ConflictException {
        userValidator.validate(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getCertificateNumber() != null && user.getCertificateNumber().endsWith("CFI")) {
            user.setRole(Role.INSTRUCTOR);
        } else {
            user.setRole(Role.STUDENT);
        }
        final User response = userService.store(user);
        notificationService.send(
                response.getId(),
                null,
                null,
                NotificationType.ALL,
                NotificationEventType.USER_SETTINGS);
        return response;
    }

    /**
     * Updates a user
     *
     * @param user User
     * @param principal Principal
     * @return User
     * @throws ResourceNotFoundException when no user is found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws InvalidPayloadException when invalid data is provided
     * @throws ConflictException when user data conflict with another user
     */
    @PutMapping
    public User put(@RequestBody User user, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException, InvalidPayloadException, ConflictException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        userValidator.validate(user);
        final User loggedInUser = userService.findByUsername(principal.getName());
        final Role role = loggedInUser.getRole();
        if (role != Role.ADMIN && role != Role.INSTRUCTOR && loggedInUser.getId() != user.getId()) {
            throw new AccessDeniedException("Current user is not authorized to update user information");
        }
        final User response = userService.store(user);
        notificationService.send(
                response.getId(),
                null,
                null,
                NotificationType.ALL,
                NotificationEventType.USER_SETTINGS);
        return response;
    }

    /**
     * Gets a user
     *
     * @param userId Long
     * @param principal Principal
     * @return User
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws ResourceNotFoundException when user is not found
     */
    @GetMapping(path = {
            "/{userId}"
    })
    public User get(@PathVariable("userId") long userId, Principal principal) throws AccessDeniedException,
            ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        final User loggedInUser = userService.findByUsername(principal.getName());
        final Role role = loggedInUser.getRole();
        if (role != Role.ADMIN && role != Role.INSTRUCTOR && loggedInUser.getId() != userId) {
            throw new AccessDeniedException("Current user is not authorized to update user information");
        }
        return userService.get(userId);
    }

    /**
     * Checks to see if a username is available
     *
     * @param username to verify
     * @return success
     * @throws ResourceNotFoundException when user is not found
     */
    @GetMapping(path = {
            "/username/{username}/available"
    })
    public boolean checkUsername(@PathVariable("username") String username) throws ResourceNotFoundException {
        return userService.findByUsername(username) != null ? false : true;
    }

    /**
     * Get all users
     *
     * @param principal Principal
     * @return list of Users
     * @throws ResourceNotFoundException when user is not found
     */
    @GetMapping
    public List<User> list(Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return userService.getAll();
    }

    /**
     * Verifies a user's notification settings for a given NotificationType
     *
     * @param userId user ID
     * @param type NotificationType
     * @throws ResourceNotFoundException when no user is found
     */
    @GetMapping(path = {
            "/{userId}/verify/{type}"
    })
    public void verify(@PathVariable("userId") long userId, @PathVariable("type") NotificationType type)
            throws ResourceNotFoundException {
        final User user = userService.get(userId);
        if (user != null) {
            switch (type) {
                case EMAIL:
                    user.setEmailVerified(true);
                    break;
                case SLACK:
                    user.setSlackVerified(true);
                    break;
                default:
            }
            userService.store(user);
            notificationService.send(userId, null, null, NotificationType.ALL, NotificationEventType.USER_VERIFIED);
        }
    }

    /**
     * Updates a user's password
     *
     * @param userId User ID
     * @param password new password
     * @param verificationCode to ensure request is not fraudulent
     * @return success
     * @throws ResourceNotFoundException when no user is found
     *
     */
    @PostMapping(path = {
            "/{userId}/password/{verificationCode}"
    })
    public boolean updatePassword(
            @PathVariable("userId") long userId,
            @PathVariable("verificationCode") String verificationCode,
            @RequestBody String password) throws ResourceNotFoundException {
        final User user = userService.get(userId);
        if (user == null) {
            final String msg = String.format("No user found for ID [%s]", userId);
            LOGGER.warn(msg);
            throw new ResourceNotFoundException(msg);
        }
        if (verificationCode != null && verificationCode.equals(user.getCode())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setCode(null);
            return userService.store(user) == null ? false : true;
        }
        return false;
    }

    /**
     * Start the user password reset process
     *
     * @param userId User ID
     * @return success
     * @throws ResourceNotFoundException when no user is found
     *
     */
    @PostMapping(path = {
            "/{userId}/password/reset"
    })
    public boolean passwordReset(@PathVariable("userId") long userId) throws ResourceNotFoundException {
        final User user = userService.get(userId);
        if (user == null) {
            final String msg = String.format("No user found for ID [%s]", userId);
            LOGGER.warn(msg);
            throw new ResourceNotFoundException(msg);
        }
        // TODO do something with verificationCode
        user.setCode(CodeGenerator.generateCode(4));
        userService.store(user);
        notificationService.send(userId, null, null, NotificationType.ALL, NotificationEventType.PASSWORD_RESET);
        return true;
    }

    /**
     * Logout
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @PostMapping(path = {
            "/logout"
    })
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    /**
     * Invite
     *
     * @param email Email address of user being invited
     * @param principal Principal
     * @throws ResourceNotFoundException when user is not found
     */
    @PostMapping(path = {
            "/invite"
    })
    public void invite(@RequestBody String email, Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        notificationService.invite(null, email);
    }

}
