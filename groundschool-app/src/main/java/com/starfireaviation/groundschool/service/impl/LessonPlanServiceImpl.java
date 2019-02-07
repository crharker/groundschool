/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.model.Activity;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.sql.ActivityEntity;
import com.starfireaviation.groundschool.model.sql.LessonPlanEntity;
import com.starfireaviation.groundschool.repository.ActivityRepository;
import com.starfireaviation.groundschool.repository.LessonPlanRepository;
import com.starfireaviation.groundschool.service.LessonPlanService;

import ma.glasnost.orika.MapperFacade;

/**
 * LessonPlanServiceImpl
 *
 * @author brianmichael
 */
@Service
public class LessonPlanServiceImpl implements LessonPlanService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonPlanServiceImpl.class);

    /**
     * LessonPlanRepository
     */
    @Autowired
    private LessonPlanRepository lessonPlanRepository;

    /**
     * ActivityRepository
     */
    @Autowired
    private ActivityRepository activityRepository;

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
     * LessonPlanCache
     */
    private Map<Long, LessonPlan> lessonPlanCache;

    /**
     * Initializes an instance of <code>LessonPlanServiceImpl</code> with the default data.
     */
    public LessonPlanServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>LessonPlanServiceImpl</code> with the default data.
     *
     * @param lessonPlanRepository LessonPlanRepository
     * @param mapperFacade MapperFacade
     * @param hazelcastInstance HazelcastInstance
     */
    public LessonPlanServiceImpl(
            LessonPlanRepository lessonPlanRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
        this.lessonPlanRepository = lessonPlanRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan store(LessonPlan lessonPlan) {
        if (lessonPlan == null) {
            return lessonPlan;
        }
        if (lessonPlan.getId() != null) {
            initCache();
            lessonPlanCache.remove(lessonPlan.getId());
        }
        return mapper.map(lessonPlanRepository.save(mapper.map(lessonPlan, LessonPlanEntity.class)), LessonPlan.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan delete(long id) {
        LessonPlan lessonPlan = mapper.map(get(id), LessonPlan.class);
        if (lessonPlan != null) {
            initCache();
            lessonPlanCache.remove(id);
            lessonPlanRepository.delete(mapper.map(lessonPlan, LessonPlanEntity.class));
        }
        // TODO cascade to delete activities
        return lessonPlan;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<LessonPlan> getAll() {
        List<LessonPlan> lessonPlans = new ArrayList<>();
        List<LessonPlanEntity> lessonPlanEntities = lessonPlanRepository.findAll();
        for (LessonPlanEntity lessonPlanEntity : lessonPlanEntities) {
            lessonPlans.add(get(lessonPlanEntity.getId()));
        }
        return lessonPlans;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan get(long id) {
        initCache();
        if (lessonPlanCache.containsKey(id)) {
            return lessonPlanCache.get(id);
        }
        final LessonPlan lessonPlan = mapper.map(lessonPlanRepository.findById(id), LessonPlan.class);
        final List<ActivityEntity> activityEntities = activityRepository.findActivityByLessonPlanId(id);
        if (activityEntities != null) {
            ForkJoinPool forkJoinPool = new ForkJoinPool(4);
            List<Activity> activities = new ArrayList<>();
            try {
                activities.addAll(
                        forkJoinPool
                                .submit(
                                        () -> activityEntities
                                                .parallelStream()
                                                .map(activity -> mapper.map(activity, Activity.class))
                                                .collect(
                                                        Collectors.toList()))
                                .get());
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.info("Unable to process lesson plan activities");
            }
            lessonPlan.setActivities(activities);
        }
        lessonPlanCache.put(id, lessonPlan);
        return lessonPlan;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (lessonPlanCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            lessonPlanCache = hazelcastInstance.getMap("lessonplans");
        }
    }

}
