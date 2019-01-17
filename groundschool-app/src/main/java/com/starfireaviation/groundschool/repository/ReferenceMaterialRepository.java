/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.ReferenceMaterialEntity;

/**
 * ReferenceMaterialRepository
 *
 * @author brianmichael
 */
public interface ReferenceMaterialRepository extends Repository<ReferenceMaterialEntity, Long> {

    /**
     * Deletes a ReferenceMaterial
     *
     * @param referenceMaterial ReferenceMaterialEntity
     */
    void delete(ReferenceMaterialEntity referenceMaterial);

    /**
     * Gets all referenceMaterial
     *
     * @return list of ReferenceMaterialEntity
     */
    List<ReferenceMaterialEntity> findAll();

    /**
     * Gets a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     */
    ReferenceMaterialEntity findById(long id);

    /**
     * Saves a referenceMaterial
     *
     * @param referenceMaterial ReferenceMaterial
     * @return ReferenceMaterial
     */
    ReferenceMaterialEntity save(ReferenceMaterialEntity referenceMaterial);
}
