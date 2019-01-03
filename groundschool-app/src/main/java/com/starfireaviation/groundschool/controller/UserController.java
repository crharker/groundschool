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

import com.starfireaviation.groundschool.model.User;
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
     * Initializes an instance of <code>UserController</code> with the default data.
     */
    public UserController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserController</code> with the default data.
     *
     * @param userService UserService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a user
     *
     * @param user User
     * @return User
     */
    @PostMapping
    public User post(@RequestBody User user) {
        if (user == null) {
            return user;
        }
        return userService.store(user);
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
        return userService.findUserById(id);
    }

    /**
     * Updates a user
     *
     * @param user User
     * @return User
     */
    @PutMapping
    public User put(@RequestBody User user) {
        if (user == null) {
            return user;
        }
        return userService.store(user);
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
        return userService.delete(id);
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
}
