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

import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.AnswerEntity;
import com.starfireaviation.groundschool.model.sql.MemberDetailsEntity;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.model.sql.ReferenceMaterialEntity;
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

        orikaMapperFactoryConfig.configure(mapperFactory);

        assertUserMapper();
        //assertRoleMapper();
        assertQuestionMapper();
        assertAnswerMapper();
        assertReferenceMaterialMapper();
        assertMemberDetailsMapper();
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

}
