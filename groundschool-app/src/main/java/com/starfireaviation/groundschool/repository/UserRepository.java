/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.UserEntity;

/**
 * UserRepository
 *
 * @author brianmichael
 */
public interface UserRepository extends Repository<UserEntity, Long> {

    /**
     * Deletes a user
     *
     * @param user User
     */
    void delete(UserEntity user);

    /**
     * Gets all users
     *
     * @return list of Users
     */
    List<UserEntity> findAll();

    /**
     * Gets a user by ID
     *
     * @param id Long
     * @return User
     */
    UserEntity findById(long id);

    /**
     * Gets a user by Username
     *
     * @param username String
     * @return User
     */
    UserEntity findByUsername(String username);

    /**
     * Gets a user by SMS Number
     *
     * @param sms String
     * @return User
     */
    UserEntity findBySms(String sms);

    /**
     * Gets a user by Slack name
     *
     * @param slack String
     * @return User
     */
    UserEntity findBySlack(String slack);

    /**
     * Gets a user by Email Address
     *
     * @param email String
     * @return User
     */
    UserEntity findByEmail(String email);

    /**
     * Gets a user by EAA Number
     *
     * @param eaaNumber String
     * @return User
     */
    UserEntity findByEaaNumber(String eaaNumber);

    /**
     * Saves a user
     *
     * @param user User
     * @return User
     */
    UserEntity save(UserEntity user);
}
