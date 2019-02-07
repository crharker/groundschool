/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
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
     * HazelcastInstance
     */
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * ReferenceMaterialCache
     */
    private Map<Long, ReferenceMaterial> referenceMaterialCache;

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
     * @param hazelcastInstance HazelcastInstance
     */
    public ReferenceMaterialServiceImpl(
            ReferenceMaterialRepository referenceMaterialRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
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
        if (referenceMaterial.getId() != null) {
            initCache();
            referenceMaterialCache.remove(referenceMaterial.getId());
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
        ReferenceMaterial referenceMaterial = mapper.map(get(id), ReferenceMaterial.class);
        if (referenceMaterial != null) {
            initCache();
            referenceMaterialCache.remove(id);
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
    public List<ReferenceMaterial> getAll() {
        List<ReferenceMaterial> referenceMaterials = new ArrayList<>();
        List<ReferenceMaterialEntity> referenceMaterialEntities = referenceMaterialRepository.findAll();
        for (ReferenceMaterialEntity referenceMaterialEntity : referenceMaterialEntities) {
            referenceMaterials.add(get(referenceMaterialEntity.getId()));
        }
        return referenceMaterials;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<ReferenceMaterial> findByQuestionId(long questionId) {
        List<ReferenceMaterial> referenceMaterials = new ArrayList<>();
        List<QuestionReferenceMaterialEntity> questionReferenceMaterialEntities = questionReferenceMaterialRepository
                .findByQuestionId(questionId);
        if (questionReferenceMaterialEntities != null) {
            questionReferenceMaterialEntities
                    .parallelStream()
                    .map(
                            questionReferenceMaterialEntity -> get(
                                    questionReferenceMaterialEntity.getReferenceMaterialId()))
                    .collect(Collectors.toList());
        }
        return referenceMaterials;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public ReferenceMaterial get(long id) {
        initCache();
        if (referenceMaterialCache.containsKey(id)) {
            return referenceMaterialCache.get(id);
        }
        final ReferenceMaterial referenceMaterial = mapper.map(
                referenceMaterialRepository.findById(id),
                ReferenceMaterial.class);
        referenceMaterialCache.put(id, referenceMaterial);
        return referenceMaterial;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (referenceMaterialCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            referenceMaterialCache = hazelcastInstance.getMap("referencematerials");
        }
    }

}
