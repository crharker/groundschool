/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.model.Lesson;
import com.starfireaviation.groundschool.model.sql.LessonEntity;
import com.starfireaviation.groundschool.repository.LessonRepository;
import com.starfireaviation.groundschool.service.LessonService;

import ma.glasnost.orika.MapperFacade;

/**
 * LessonServiceImpl
 *
 * @author brianmichael
 */
@Service
public class LessonServiceImpl implements LessonService {

    /**
     * LessonRepository
     */
    @Autowired
    private LessonRepository lessonRepository;

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
     * LessonCache
     */
    private Map<Long, Lesson> lessonCache;

    /**
     * Initializes an instance of <code>LessonServiceImpl</code> with the default data.
     */
    public LessonServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>LessonServiceImpl</code> with the default data.
     *
     * @param lessonRepository LessonRepository
     * @param mapperFacade MapperFacade
     * @param hazelcastInstance HazelcastInstance
     */
    public LessonServiceImpl(
            LessonRepository lessonRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
        this.lessonRepository = lessonRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Lesson store(Lesson lesson) {
        if (lesson == null) {
            return lesson;
        }
        if (lesson.getId() != null) {
            initCache();
            lessonCache.remove(lesson.getId());
        }
        return mapper.map(lessonRepository.save(mapper.map(lesson, LessonEntity.class)), Lesson.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Lesson delete(long id) {
        Lesson lesson = mapper.map(get(id), Lesson.class);
        if (lesson != null) {
            initCache();
            lessonCache.remove(id);
            lessonRepository.delete(mapper.map(lesson, LessonEntity.class));
        }
        return lesson;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Lesson> getAll() {
        List<Lesson> lessons = new ArrayList<>();
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        for (LessonEntity lessonEntity : lessonEntities) {
            lessons.add(get(lessonEntity.getId()));
        }
        return lessons;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Lesson get(long id) {
        initCache();
        if (lessonCache.containsKey(id)) {
            return lessonCache.get(id);
        }
        final Lesson lesson = mapper.map(lessonRepository.findById(id), Lesson.class);
        lessonCache.put(id, lesson);
        return lesson;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (lessonCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            lessonCache = hazelcastInstance.getMap("lessons");
        }
    }

}
