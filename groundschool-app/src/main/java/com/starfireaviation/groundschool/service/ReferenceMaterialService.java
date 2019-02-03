/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

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
     */
    public ReferenceMaterial delete(long id);

    /**
     * Gets all referenceMaterials
     *
     * @return list of ReferenceMaterial
     */
    public List<ReferenceMaterial> findAllReferenceMaterials();

    /**
     * Gets a referenceMaterial
     *
     * @param questionId Long
     * @return list of ReferenceMaterial
     */
    public List<ReferenceMaterial> findByQuestionId(long questionId);

    /**
     * Gets a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     */
    public ReferenceMaterial findReferenceMaterialById(long id);

}
