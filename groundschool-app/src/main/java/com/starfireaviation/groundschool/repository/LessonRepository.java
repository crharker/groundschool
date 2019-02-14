/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.LessonEntity;

/**
 * LessonRepository
 *
 * @author brianmichael
 */
public interface LessonRepository extends Repository<LessonEntity, Long> {

    /**
     * Deletes a lesson
     *
     * @param lesson LessonEntity
     */
    void delete(LessonEntity lesson);

    /**
     * Gets all lesson
     *
     * @return list of LessonEntity
     */
    List<LessonEntity> findAll();

    /**
     * Gets a lesson
     *
     * @param id Long
     * @return LessonEntity
     */
    LessonEntity findById(long id);

    /**
     * Saves a lesson
     *
     * @param lesson LessonEntity
     * @return LessonEntity
     */
    LessonEntity save(LessonEntity lesson);
}
