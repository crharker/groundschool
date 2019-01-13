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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.UserService;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * Initializes an instance of <code>UserController</code> with the default data.
     */
    public UserController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserController</code> with the default data.
     *
     * @param userService UserService
     * @param notificationService NotificationService
     */
    public UserController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    /**
     * Creates a user
     *
     * @param user User
     * @return User
     */
    @PostMapping
    public User post(@RequestBody User user) {
        return storeUser(user);
    }

    /**
     * Updates a user
     *
     * @param user User
     * @param principal Principal
     * @return User
     */
    @PutMapping
    public User put(@RequestBody User user, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return storeUser(user);
    }

    /**
     * Gets a user
     *
     * @param userId Long
     * @param principal Principal
     * @return User
     */
    @GetMapping(path = {
            "/{userId}"
    })
    public User get(@PathVariable("userId") long userId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return userService.findById(userId);
    }

    /**
     * Deletes a user
     *
     * @param userId Long
     * @param principal Principal
     * @return User
     */
    @DeleteMapping(path = {
            "/{userId}"
    })
    public User delete(@PathVariable("userId") long userId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        final User response = userService.delete(userId);
        notificationService.send(response.getId(), NotificationType.ALL, NotificationEventType.USER_DELETE);
        return response;
    }

    /**
     * Get all users
     *
     * @param principal Principal
     * @return list of Users
     */
    @GetMapping
    public List<User> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return userService.findAllUsers();
    }

    /**
     * Verifies a user's notification settings for a given NotificationType
     *
     * @param userId user ID
     * @param type NotificationType
     */
    @GetMapping(path = {
            "/{userId}/verify/{type}"
    })
    public void verify(@PathVariable("userId") long userId, @PathVariable("type") NotificationType type) {
        final User user = userService.findById(userId);
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
            notificationService.send(userId, NotificationType.ALL, NotificationEventType.USER_VERIFIED);
        }
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
     * Checkin
     *
     * @param userId User ID
     * @param eventId Event ID
     * @param code checkin code
     * @param principal Principal
     * @return checkin success
     */
    @PostMapping(path = {
            "/{userId}/checkin/{eventId}/{code}"
    })
    public boolean checkin(
            @PathVariable("userId") long userId,
            @PathVariable("eventId") long eventId,
            @PathVariable("code") String code,
            Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return eventService.checkin(eventId, userId, code);
    }

    /**
     * Invite
     *
     * @param email Email address of user being invited
     * @param principal Principal
     */
    @PostMapping(path = {
            "/invite"
    })
    public void invite(@RequestBody String email, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        notificationService.invite(null, email);
    }

    /**
     * Combine POST and PUT operations
     *
     * @param user User
     * @return User
     */
    private User storeUser(User user) {
        if (user == null) {
            return user;
        }
        final User response = userService.store(user);
        notificationService.send(response.getId(), NotificationType.ALL, NotificationEventType.USER_SETTINGS);
        return response;
    }

}
