/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

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
                .classMap(MemberDetails.class, MemberDetailsEntity.class)
                .byDefault()
                .register();
        mapperFactory
                .classMap(Question.class, QuestionEntity.class)
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
    }
}
