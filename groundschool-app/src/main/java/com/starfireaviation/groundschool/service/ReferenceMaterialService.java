/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.ReferenceMaterial;

/**
 * ReferenceMaterialService
 *
 * @author brianmichael
 */
public interface ReferenceMaterialService {

    /**
     * Creates a referenceMaterial
     *
     * @param referenceMaterial ReferenceMaterial
     * @return ReferenceMaterial
     */
    public ReferenceMaterial store(ReferenceMaterial referenceMaterial);

    /**
     * Deletes a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    public ReferenceMaterial delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all referenceMaterials
     *
     * @return list of ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    public List<ReferenceMaterial> getAll() throws ResourceNotFoundException;

    /**
     * Gets a referenceMaterial
     *
     * @param questionId Long
     * @return list of ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    public List<ReferenceMaterial> findByQuestionId(long questionId) throws ResourceNotFoundException;

    /**
     * Gets a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    public ReferenceMaterial get(long id) throws ResourceNotFoundException;

}
