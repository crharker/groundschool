/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.UserService;

import java.util.List;

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
     * UserService
     */
    @Autowired
    private UserService userService;

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
     * @return User
     */
    @PutMapping
    public User put(@RequestBody User user) {
        return storeUser(user);
    }

    /**
     * Gets a user
     *
     * @param id Long
     * @return User
     */
    @GetMapping(path = {
            "/{id}"
    })
    public User get(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    /**
     * Deletes a user
     *
     * @param id Long
     * @return User
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public User delete(@PathVariable("id") long id) {
        final User response = userService.delete(id);
        notificationService.send(response.getId(), NotificationEventType.USER_DELETE);
        return response;
    }

    /**
     * Get all users
     *
     * @return list of Users
     */
    @GetMapping
    public List<User> list() {
        return userService.findAllUsers();
    }

    /**
     * Verifies a user's notification settings for a given NotificationType
     *
     * @param userId user ID
     * @param type NotificationType
     */
    @GetMapping(path = {
            "/verify/{userId}/{type}"
    })
    public void verify(@PathVariable("userId") long userId, @PathVariable("type") NotificationType type) {
        final User user = userService.findById(userId);
        if (user != null) {
            switch (type) {
                case EMAIL:
                    user.setEmailVerified(true);
                    break;
                case SMS:
                    user.setSmsVerified(true);
                    break;
                case SLACK:
                    user.setSlackVerified(true);
                    break;
                default:
                    break;
            }
            userService.store(user);
            notificationService.send(userId, NotificationEventType.USER_VERIFIED);
        }
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
        notificationService.send(response.getId(), NotificationEventType.USER_SETTINGS);
        return response;
    }

}
