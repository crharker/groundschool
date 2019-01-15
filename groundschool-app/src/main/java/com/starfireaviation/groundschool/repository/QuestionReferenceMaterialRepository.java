/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.QuestionReferenceMaterialEntity;

/**
 * QuestionReferenceMaterialRepository
 *
 * @author brianmichael
 */
public interface QuestionReferenceMaterialRepository extends Repository<QuestionReferenceMaterialEntity, Long> {

    /**
     * Deletes a QuestionReferenceMaterialEntity
     *
     * @param questionReferenceMaterialEntity QuestionReferenceMaterialEntity
     */
    void delete(QuestionReferenceMaterialEntity questionReferenceMaterialEntity);

    /**
     * Gets all QuestionReferenceMaterialEntity
     *
     * @return list of QuestionReferenceMaterialEntity
     */
    List<QuestionReferenceMaterialEntity> findAll();

    /**
     * Gets a QuestionReferenceMaterialEntity
     *
     * @param id Long
     * @return QuestionReferenceMaterialEntity
     */
    QuestionReferenceMaterialEntity findById(long id);

    /**
     * Gets all QuestionReferenceMaterialEntity for a given Question ID
     *
     * @param id Long
     * @return QuestionReferenceMaterialEntity
     */
    List<QuestionReferenceMaterialEntity> findByQuestionId(long id);

    /**
     * Gets all QuestionReferenceMaterialEntity for a given ReferenceMaterial ID
     *
     * @param id Long
     * @return QuestionReferenceMaterialEntity
     */
    List<QuestionReferenceMaterialEntity> findByReferenceMaterialId(long id);

    /**
     * Saves a QuestionReferenceMaterialEntity
     *
     * @param questionReferenceMaterialEntity QuestionReferenceMaterialEntity
     * @return QuestionReferenceMaterialEntity
     */
    QuestionReferenceMaterialEntity save(QuestionReferenceMaterialEntity questionReferenceMaterialEntity);
}
