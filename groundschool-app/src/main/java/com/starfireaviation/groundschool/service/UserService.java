/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

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
     */
    public User store(User user);

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
    public List<User> findAll();

    /**
     * Gets a user
     *
     * @param id Long
     * @return User
     */
    public User findById(long id);

}
