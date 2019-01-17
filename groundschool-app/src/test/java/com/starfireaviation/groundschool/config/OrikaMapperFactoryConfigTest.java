/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.AddressEntity;
import com.starfireaviation.groundschool.model.sql.AnswerEntity;
import com.starfireaviation.groundschool.model.sql.EventEntity;
import com.starfireaviation.groundschool.model.sql.LessonPlanEntity;
import com.starfireaviation.groundschool.model.sql.MemberDetailsEntity;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.model.sql.QuizEntity;
import com.starfireaviation.groundschool.model.sql.ReferenceMaterialEntity;
import com.starfireaviation.groundschool.model.sql.StatisticEntity;
import com.starfireaviation.groundschool.model.sql.UserEntity;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

/**
 * OrikaMapperFactoryConfigTests
 *
 * @author brianmichael
 */
public class OrikaMapperFactoryConfigTest {

    /**
     * MapperFactory
     */
    @Mock
    private MapperFactory mapperFactory;

    /**
     * ClassMapBuilder userClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<User, UserEntity> userClassMapBuilder;

    /**
     * ClassMapBuilder questionClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Question, QuestionEntity> questionClassMapBuilder;

    /**
     * ClassMapBuilder answerClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Answer, AnswerEntity> answerClassMapBuilder;

    /**
     * ClassMapBuilder addressClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Address, AddressEntity> addressClassMapBuilder;

    /**
     * ClassMapBuilder referenceMaterialClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<ReferenceMaterial, ReferenceMaterialEntity> referenceMaterialClassMapBuilder;

    /**
     * ClassMapBuilder memberDetailsClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<MemberDetails, MemberDetailsEntity> memberDetailsClassMapBuilder;

    /**
     * ClassMapBuilder lessonPlanClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<LessonPlan, LessonPlanEntity> lessonPlanClassMapBuilder;

    /**
     * ClassMapBuilder quizClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Quiz, QuizEntity> quizClassMapBuilder;

    /**
     * ClassMapBuilder statisticClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Statistic, StatisticEntity> statisticClassMapBuilder;

    /**
     * ClassMapBuilder eventClassMapBuilder
     */
    @Mock
    private ClassMapBuilder<Event, EventEntity> eventClassMapBuilder;

    /**
     * OrikaMapperFactoryConfig
     */
    private OrikaMapperFactoryConfig orikaMapperFactoryConfig;

    /**
     * Test setup
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        orikaMapperFactoryConfig = new OrikaMapperFactoryConfig();
    }

    /**
     * Test configure operation
     */
    @Test
    public void configureTest() {
        initUserMapper();
        //initRoleMapper();
        initQuestionMapper();
        initAnswerMapper();
        initReferenceMaterialMapper();
        initMemberDetailsMapper();
        initLessonPlanMapper();
        initQuizMapper();
        initStatisticMapper();
        initEventMapper();
        initAddressMapper();

        orikaMapperFactoryConfig.configure(mapperFactory);

        assertUserMapper();
        //assertRoleMapper();
        assertQuestionMapper();
        assertAnswerMapper();
        assertReferenceMaterialMapper();
        assertMemberDetailsMapper();
        assertLessonPlanMapper();
        assertQuizMapper();
        assertStatisticMapper();
        assertEventMapper();
        assertAddressMapper();
    }

    /**
     * UserMapper
     */
    private void initUserMapper() {
        Mockito.when(mapperFactory.classMap(User.class, UserEntity.class)).thenReturn(
                userClassMapBuilder);
        Mockito.when(userClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                userClassMapBuilder);
        Mockito.when(userClassMapBuilder.byDefault()).thenReturn(
                userClassMapBuilder);
        Mockito.when(userClassMapBuilder.customize(ArgumentMatchers.any(Mapper.class))).thenReturn(
                userClassMapBuilder);
        Mockito
                .when(
                        userClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(userClassMapBuilder);
    }

    /**
     * UserMapper
     */
    private void assertUserMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(User.class, UserEntity.class);
        Mockito.verify(userClassMapBuilder, Mockito.times(1)).byDefault();
        //        Mockito
        //                .verify(
        //                        userClassMapBuilder,
        //                        Mockito.times(1))
        //                .customize(ArgumentMatchers.<Mapper<User, UserEntity>>any());
        Mockito.verify(userClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * QuestionMapper
     */
    private void initQuestionMapper() {
        Mockito.when(mapperFactory.classMap(Question.class, QuestionEntity.class)).thenReturn(
                questionClassMapBuilder);
        Mockito.when(questionClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                questionClassMapBuilder);
        Mockito.when(questionClassMapBuilder.byDefault()).thenReturn(
                questionClassMapBuilder);
        Mockito
                .when(
                        questionClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(questionClassMapBuilder);
    }

    /**
     * QuestionMapper
     */
    private void assertQuestionMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Question.class, QuestionEntity.class);
        Mockito.verify(questionClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(questionClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * AnswerMapper
     */
    private void initAnswerMapper() {
        Mockito.when(mapperFactory.classMap(Answer.class, AnswerEntity.class)).thenReturn(
                answerClassMapBuilder);
        Mockito.when(answerClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                answerClassMapBuilder);
        Mockito.when(answerClassMapBuilder.byDefault()).thenReturn(
                answerClassMapBuilder);
        Mockito
                .when(
                        answerClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(answerClassMapBuilder);
    }

    /**
     * AnswerMapper
     */
    private void assertAnswerMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Answer.class, AnswerEntity.class);
        Mockito.verify(answerClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(answerClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * ReferenceMaterialMapper
     */
    private void initReferenceMaterialMapper() {
        Mockito.when(mapperFactory.classMap(ReferenceMaterial.class, ReferenceMaterialEntity.class)).thenReturn(
                referenceMaterialClassMapBuilder);
        Mockito.when(referenceMaterialClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                referenceMaterialClassMapBuilder);
        Mockito.when(referenceMaterialClassMapBuilder.byDefault()).thenReturn(
                referenceMaterialClassMapBuilder);
        Mockito
                .when(
                        referenceMaterialClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(referenceMaterialClassMapBuilder);
    }

    /**
     * ReferenceMaterialMapper
     */
    private void assertReferenceMaterialMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(
                ReferenceMaterial.class,
                ReferenceMaterialEntity.class);
        Mockito.verify(referenceMaterialClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(referenceMaterialClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * MemberDetailsMapper
     */
    private void initMemberDetailsMapper() {
        Mockito.when(mapperFactory.classMap(MemberDetails.class, MemberDetailsEntity.class)).thenReturn(
                memberDetailsClassMapBuilder);
        Mockito.when(memberDetailsClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                memberDetailsClassMapBuilder);
        Mockito.when(memberDetailsClassMapBuilder.byDefault()).thenReturn(
                memberDetailsClassMapBuilder);
        Mockito
                .when(
                        memberDetailsClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(memberDetailsClassMapBuilder);
    }

    /**
     * MemberDetailsMapper
     */
    private void assertMemberDetailsMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(MemberDetails.class, MemberDetailsEntity.class);
        Mockito.verify(memberDetailsClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(memberDetailsClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * LessonPlanMapper
     */
    private void initLessonPlanMapper() {
        Mockito.when(mapperFactory.classMap(LessonPlan.class, LessonPlanEntity.class)).thenReturn(
                lessonPlanClassMapBuilder);
        Mockito.when(lessonPlanClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                lessonPlanClassMapBuilder);
        Mockito.when(lessonPlanClassMapBuilder.byDefault()).thenReturn(
                lessonPlanClassMapBuilder);
        Mockito
                .when(
                        lessonPlanClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(lessonPlanClassMapBuilder);
    }

    /**
     * LessonPlanMapper
     */
    private void assertLessonPlanMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(LessonPlan.class, LessonPlanEntity.class);
        Mockito.verify(lessonPlanClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(lessonPlanClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * QuizMapper
     */
    private void initQuizMapper() {
        Mockito.when(mapperFactory.classMap(Quiz.class, QuizEntity.class)).thenReturn(
                quizClassMapBuilder);
        Mockito.when(quizClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                quizClassMapBuilder);
        Mockito.when(quizClassMapBuilder.byDefault()).thenReturn(
                quizClassMapBuilder);
        Mockito
                .when(
                        quizClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(quizClassMapBuilder);
    }

    /**
     * QuizMapper
     */
    private void assertQuizMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Quiz.class, QuizEntity.class);
        Mockito.verify(quizClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(quizClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * StatisticMapper
     */
    private void initStatisticMapper() {
        Mockito.when(mapperFactory.classMap(Statistic.class, StatisticEntity.class)).thenReturn(
                statisticClassMapBuilder);
        Mockito.when(statisticClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                statisticClassMapBuilder);
        Mockito.when(statisticClassMapBuilder.byDefault()).thenReturn(
                statisticClassMapBuilder);
        Mockito
                .when(
                        statisticClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(statisticClassMapBuilder);
    }

    /**
     * StatisticMapper
     */
    private void assertStatisticMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Statistic.class, StatisticEntity.class);
        Mockito.verify(statisticClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(statisticClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * EventMapper
     */
    private void initEventMapper() {
        Mockito.when(mapperFactory.classMap(Event.class, EventEntity.class)).thenReturn(
                eventClassMapBuilder);
        Mockito.when(eventClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                eventClassMapBuilder);
        Mockito.when(eventClassMapBuilder.byDefault()).thenReturn(
                eventClassMapBuilder);
        Mockito
                .when(
                        eventClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(eventClassMapBuilder);
    }

    /**
     * EventMapper
     */
    private void assertEventMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Event.class, EventEntity.class);
        Mockito.verify(eventClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(eventClassMapBuilder, Mockito.times(1)).register();
    }

    /**
     * AddressMapper
     */
    private void initAddressMapper() {
        Mockito.when(mapperFactory.classMap(Address.class, AddressEntity.class)).thenReturn(
                addressClassMapBuilder);
        Mockito.when(addressClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
                addressClassMapBuilder);
        Mockito.when(addressClassMapBuilder.byDefault()).thenReturn(
                addressClassMapBuilder);
        Mockito
                .when(
                        addressClassMapBuilder.field(
                                ArgumentMatchers.anyString(),
                                ArgumentMatchers.anyString()))
                .thenReturn(addressClassMapBuilder);
    }

    /**
     * AddressMapper
     */
    private void assertAddressMapper() {
        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Address.class, AddressEntity.class);
        Mockito.verify(addressClassMapBuilder, Mockito.times(1)).byDefault();
        Mockito.verify(addressClassMapBuilder, Mockito.times(1)).register();
    }

}
