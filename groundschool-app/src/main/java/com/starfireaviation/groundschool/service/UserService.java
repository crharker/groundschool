/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.User;

/**
 * UserService
 *
 * @author brianmichael
 */
public interface UserService {

    /**
     * Creates a user
     *
     * @param user User
     * @return User
     * @throws ResourceNotFoundException when no user is found for the provided user ID
     */
    public User store(User user) throws ResourceNotFoundException;

    /**
     * Deletes a user
     *
     * @param id Long
     * @return User
     */
    public User delete(long id);

    /**
     * Gets all users
     *
     * @return list of Users
     */
    public List<User> findAllUsers();

    /**
     * Gets a user
     *
     * @param id Long
     * @return User
     */
    public User findById(long id);

    /**
     * Gets a user by username
     *
     * @param username username
     * @return User
     */
    public User findByUsername(String username);

}
