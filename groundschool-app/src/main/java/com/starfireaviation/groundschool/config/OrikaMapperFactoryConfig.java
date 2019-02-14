/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import com.starfireaviation.groundschool.model.Activity;
import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.Lesson;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.ActivityEntity;
import com.starfireaviation.groundschool.model.sql.AddressEntity;
import com.starfireaviation.groundschool.model.sql.AnswerEntity;
import com.starfireaviation.groundschool.model.sql.EventEntity;
import com.starfireaviation.groundschool.model.sql.LessonEntity;
import com.starfireaviation.groundschool.model.sql.LessonPlanEntity;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.model.sql.QuizEntity;
import com.starfireaviation.groundschool.model.sql.ReferenceMaterialEntity;
import com.starfireaviation.groundschool.model.sql.StatisticEntity;
import com.starfireaviation.groundschool.model.sql.UserEntity;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

/**
 * OrikaMapperFactoryConfig
 *
 * @author brianmichael
 */
public class OrikaMapperFactoryConfig implements OrikaMapperFactoryConfigurer {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory
                .classMap(User.class, UserEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Question.class, QuestionEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Activity.class, ActivityEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Answer.class, AnswerEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(ReferenceMaterial.class, ReferenceMaterialEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(LessonPlan.class, LessonPlanEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Lesson.class, LessonEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Quiz.class, QuizEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Statistic.class, StatisticEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Event.class, EventEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Address.class, AddressEntity.class)
                .byDefault()
                .register();
    }
}
