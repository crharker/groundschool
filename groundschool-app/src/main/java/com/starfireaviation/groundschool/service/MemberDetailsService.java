/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.MemberDetails;

/**
 * MemberDetailsService
 *
 * @author brianmichael
 */
public interface MemberDetailsService {

    /**
     * Creates a memberDetails
     *
     * @param memberDetails MemberDetails
     * @return MemberDetails
     */
    public MemberDetails store(MemberDetails memberDetails);

    /**
     * Deletes a memberDetails
     *
     * @param id Long
     * @return MemberDetails
     */
    public MemberDetails delete(long id);

    /**
     * Gets all memberDetails
     *
     * @return list of MemberDetails
     */
    public List<MemberDetails> findAllMemberDetails();

    /**
     * Gets a memberDetails
     *
     * @param id Long
     * @return MemberDetails
     */
    public MemberDetails findById(long id);

}
