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

import com.starfireaviation.groundschool.model.User;
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
        initRoleMapper();

        orikaMapperFactoryConfig.configure(mapperFactory);

        assertUserMapper();
        assertRoleMapper();
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
     * RoleMapper
     */
    private void initRoleMapper() {
        //        Mockito.when(mapperFactory.classMap(Role.class, RoleEntity.class)).thenReturn(
        //                roleClassMapBuilder);
        //        Mockito.when(roleClassMapBuilder.exclude(ArgumentMatchers.any())).thenReturn(
        //                roleClassMapBuilder);
        //        Mockito.when(roleClassMapBuilder.byDefault()).thenReturn(
        //                roleClassMapBuilder);
        //        Mockito.when(roleClassMapBuilder.customize(ArgumentMatchers.any(Mapper.class))).thenReturn(
        //                roleClassMapBuilder);
        //        Mockito
        //                .when(
        //                        roleClassMapBuilder.field(
        //                                ArgumentMatchers.anyString(),
        //                                ArgumentMatchers.anyString()))
        //                .thenReturn(roleClassMapBuilder);
    }

    /**
     * RoleMapper
     */
    private void assertRoleMapper() {
        //        Mockito.verify(mapperFactory, Mockito.times(1)).classMap(Role.class, RoleEntity.class);
        //        Mockito.verify(roleClassMapBuilder, Mockito.times(1)).byDefault();
        //        Mockito
        //                .verify(
        //                        roleClassMapBuilder,
        //                        Mockito.times(1))
        //                .customize(ArgumentMatchers.<Mapper<Role, RoleEntity>>any());
        //        Mockito.verify(roleClassMapBuilder, Mockito.times(1)).register();
    }
}
