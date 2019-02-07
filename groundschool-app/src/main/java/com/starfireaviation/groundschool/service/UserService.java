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
     * Gets all users
     *
     * @return list of Users
     * @throws ResourceNotFoundException when no user is found for the provided user ID
     */
    public List<User> getAll() throws ResourceNotFoundException;

    /**
     * Gets a user
     *
     * @param id Long
     * @return User
     * @throws ResourceNotFoundException when no user is found for the provided user ID
     */
    public User get(long id) throws ResourceNotFoundException;

    /**
     * Gets a user by username
     *
     * @param username username
     * @return User
     * @throws ResourceNotFoundException when no user is found for the provided user ID
     */
    public User findByUsername(String username) throws ResourceNotFoundException;

}
