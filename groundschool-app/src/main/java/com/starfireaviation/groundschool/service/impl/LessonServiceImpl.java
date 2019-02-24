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
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Activity;
import com.starfireaviation.groundschool.model.ActivityType;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.Lesson;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.sql.LessonEntity;
import com.starfireaviation.groundschool.repository.LessonRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.LessonService;
import com.starfireaviation.groundschool.service.StatisticService;

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
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * LessonPlanService
     */
    @Autowired
    private LessonPlanService lessonPlanService;

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
    public List<Lesson> getAttendedLessons(Long userId) throws ResourceNotFoundException {
        final List<Lesson> attendedLessons = new ArrayList<>();
        final List<Statistic> statistics = statisticService.findByUserId(userId, StatisticType.EVENT_CHECKIN);
        final List<Long> eventIds = statistics.stream().map(statistic -> statistic.getEventId()).collect(
                Collectors.toList());
        eventIds
                .stream()
                .forEach(eventId -> {
                    try {
                        final Event event = eventService.get(eventId);
                        final LessonPlan lessonPlan = lessonPlanService.get(event.getLessonPlanId());
                        final List<Activity> activities = lessonPlan.getActivities();
                        final List<Long> lessonIds = activities
                                .stream()
                                .filter(activity -> activity.getActivityType() == ActivityType.LESSON)
                                .map(activity -> activity.getReferenceId())
                                .collect(
                                        Collectors.toList());
                        lessonIds
                                .stream()
                                .forEach(lessonId -> attendedLessons.add(get(lessonId)));
                    } catch (ResourceNotFoundException e) {
                        // TODO log something
                    }
                });
        return attendedLessons;
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
