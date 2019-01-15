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

import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.sql.QuestionReferenceMaterialEntity;
import com.starfireaviation.groundschool.model.sql.ReferenceMaterialEntity;
import com.starfireaviation.groundschool.repository.QuestionReferenceMaterialRepository;
import com.starfireaviation.groundschool.repository.ReferenceMaterialRepository;
import com.starfireaviation.groundschool.service.ReferenceMaterialService;
import ma.glasnost.orika.MapperFacade;

/**
 * ReferenceMaterialServiceImpl
 *
 * @author brianmichael
 */
@Service
public class ReferenceMaterialServiceImpl implements ReferenceMaterialService {

    /**
     * UserRepository
     */
    @Autowired
    private ReferenceMaterialRepository referenceMaterialRepository;

    /**
     * QuestionReferenceMaterialRepository
     */
    @Autowired
    private QuestionReferenceMaterialRepository questionReferenceMaterialRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>UserServiceImpl</code> with the default data.
     */
    public ReferenceMaterialServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserServiceImpl</code> with the default data.
     *
     * @param referenceMaterialRepository ReferenceMaterialRepository
     * @param mapperFacade MapperFacade
     */
    public ReferenceMaterialServiceImpl(
            ReferenceMaterialRepository referenceMaterialRepository,
            MapperFacade mapperFacade) {
        this.referenceMaterialRepository = referenceMaterialRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public ReferenceMaterial store(ReferenceMaterial referenceMaterial) {
        if (referenceMaterial == null) {
            return referenceMaterial;
        }
        return mapper.map(
                referenceMaterialRepository.save(mapper.map(referenceMaterial, ReferenceMaterialEntity.class)),
                ReferenceMaterial.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public ReferenceMaterial delete(long id) {
        ReferenceMaterial referenceMaterial = mapper.map(findReferenceMaterialById(id), ReferenceMaterial.class);
        if (referenceMaterial != null) {
            referenceMaterialRepository.delete(mapper.map(referenceMaterial, ReferenceMaterialEntity.class));
            for (QuestionReferenceMaterialEntity questionReferenceMaterial : questionReferenceMaterialRepository
                    .findByReferenceMaterialId(id)) {
                questionReferenceMaterialRepository.delete(questionReferenceMaterial);
            }
        }
        return referenceMaterial;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<ReferenceMaterial> findAllReferenceMaterials() {
        List<ReferenceMaterial> referenceMaterials = new ArrayList<>();
        List<ReferenceMaterialEntity> referenceMaterialEntities = referenceMaterialRepository.findAll();
        for (ReferenceMaterialEntity referenceMaterialEntity : referenceMaterialEntities) {
            referenceMaterials.add(mapper.map(referenceMaterialEntity, ReferenceMaterial.class));
        }
        return referenceMaterials;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public ReferenceMaterial findReferenceMaterialById(long id) {
        return mapper.map(referenceMaterialRepository.findById(id), ReferenceMaterial.class);
    }

}
