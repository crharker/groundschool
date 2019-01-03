/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.MemberDetailsEntity;

/**
 * MemberDetailsRepository
 *
 * @author brianmichael
 */
public interface MemberDetailsRepository extends Repository<MemberDetailsEntity, Long> {

    /**
     * Deletes a memberDetails
     *
     * @param memberDetails MemberDetailsEntity
     */
    void delete(MemberDetailsEntity memberDetails);

    /**
     * Gets all memberDetails
     *
     * @return list of MemberDetailsEntity
     */
    List<MemberDetailsEntity> findAll();

    /**
     * Gets a memberDetails
     *
     * @param id Long
     * @return MemberDetailsEntity
     */
    MemberDetailsEntity findById(long id);

    /**
     * Saves a memberDetails
     *
     * @param user User
     * @return User
     */
    MemberDetailsEntity save(MemberDetailsEntity user);
}
