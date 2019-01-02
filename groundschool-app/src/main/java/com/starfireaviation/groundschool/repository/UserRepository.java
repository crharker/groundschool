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
     * Gets a user
     *
     * @param id Long
     * @return User
     */
    UserEntity findById(long id);

    /**
     * Saves a user
     *
     * @param user User
     * @return User
     */
    UserEntity save(UserEntity user);
}
