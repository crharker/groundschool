/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.model.sql.MemberDetailsEntity;
import com.starfireaviation.groundschool.repository.MemberDetailsRepository;
import com.starfireaviation.groundschool.service.MemberDetailsService;
import ma.glasnost.orika.MapperFacade;

/**
 * MemberDetailsServiceImpl
 *
 * @author brianmichael
 */
@Service
public class MemberDetailsServiceImpl implements MemberDetailsService {

    /**
     * MemberDetailsRepository
     */
    @Autowired
    private MemberDetailsRepository memberDetailsRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>MemberDetailsServiceImpl</code> with the default data.
     */
    public MemberDetailsServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>MemberDetailsServiceImpl</code> with the default data.
     *
     * @param memberDetailsRepository MemberDetailsRepository
     * @param mapperFacade MapperFacade
     */
    public MemberDetailsServiceImpl(MemberDetailsRepository memberDetailsRepository, MapperFacade mapperFacade) {
        this.memberDetailsRepository = memberDetailsRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public MemberDetails store(MemberDetails memberDetails) {
        if (memberDetails == null) {
            return memberDetails;
        }
        return mapper.map(
                memberDetailsRepository.save(mapper.map(memberDetails, MemberDetailsEntity.class)),
                MemberDetails.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public MemberDetails delete(long id) {
        MemberDetails memberDetails = mapper.map(findById(id), MemberDetails.class);
        if (memberDetails != null) {
            memberDetailsRepository.delete(mapper.map(memberDetails, MemberDetailsEntity.class));
        }
        return memberDetails;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<MemberDetails> findAllMemberDetails() {
        List<MemberDetails> memberDetails = new ArrayList<>();
        List<MemberDetailsEntity> memberDetailsEntities = memberDetailsRepository.findAll();
        for (MemberDetailsEntity memberDetailsEntity : memberDetailsEntities) {
            memberDetails.add(mapper.map(memberDetailsEntity, MemberDetails.class));
        }
        return memberDetails;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public MemberDetails findById(long id) {
        return mapper.map(memberDetailsRepository.findById(id), MemberDetails.class);
    }

}
