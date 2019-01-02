/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import com.starfireaviation.groundschool.model.User;
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
    }
}
