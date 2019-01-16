/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * QuestionReferenceMaterialEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "QUESTION_REFERENCE_MATERIAL")
public class QuestionReferenceMaterialEntity extends BaseEntity {

    /**
     * Question ID
     */
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    /**
     * ReferenceMaterial ID
     */
    @Column(name = "reference_material_id", nullable = false)
    private Long referenceMaterialId;

    /**
     * Retrieves the value for {@link #questionId}.
     *
     * @return the current value
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Provides a value for {@link #questionId}.
     *
     * @param questionId the new value to set
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Retrieves the value for {@link #referenceMaterialId}.
     *
     * @return the current value
     */
    public Long getReferenceMaterialId() {
        return referenceMaterialId;
    }

    /**
     * Provides a value for {@link #referenceMaterialId}.
     *
     * @param referenceMaterialId the new value to set
     */
    public void setReferenceMaterialId(Long referenceMaterialId) {
        this.referenceMaterialId = referenceMaterialId;
    }

}
