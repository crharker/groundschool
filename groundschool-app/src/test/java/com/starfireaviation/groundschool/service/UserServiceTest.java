/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.UserEntity;
import com.starfireaviation.groundschool.repository.UserRepository;
import com.starfireaviation.groundschool.service.impl.UserServiceImpl;
import com.starfireaviation.groundschool.util.ObjectCreator;

import ma.glasnost.orika.MapperFacade;

/**
 * UserServiceTests
 *
 * @author brianmichael
 */
public class UserServiceTest {

    /**
     * UserService
     */
    private UserService userService;

    /**
     * MapperFacade
     */
    @Mock
    private MapperFacade mapperFacade;

    /**
     * UserRepository
     */
    @Mock
    private UserRepository userRepository;

    /**
     * BCryptPasswordEncoder
     */
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Initializes tests
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        UserEntity user = ObjectCreator.getUserEntity();

        List<UserEntity> users = new ArrayList<>();
        users.add(user);

        Mockito.doReturn(ObjectCreator.getUserEntity()).when(userRepository).findByUsername(
                ArgumentMatchers.anyString());
        Mockito.doReturn(true).when(bCryptPasswordEncoder).matches(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.doReturn(ObjectCreator.ENCRYPTED_PASSWORD).when(bCryptPasswordEncoder).encode(
                ArgumentMatchers.anyString());
        Mockito.doReturn(user).when(userRepository).findById(ArgumentMatchers.anyLong());
        Mockito.doNothing().when(userRepository).delete(ArgumentMatchers.any());
        Mockito.doReturn(users).when(userRepository).findAll();
        Mockito.doReturn(user).when(userRepository).save(ArgumentMatchers.any());
        Mockito.doReturn(ObjectCreator.getUser()).when(mapperFacade).map(
                ArgumentMatchers.any(),
                ArgumentMatchers.eq(User.class));
        Mockito.doReturn(ObjectCreator.getUserEntity()).when(mapperFacade).map(
                ArgumentMatchers.any(),
                ArgumentMatchers.eq(UserEntity.class));

        userService = new UserServiceImpl(userRepository, mapperFacade, bCryptPasswordEncoder);
    }

    /**
     * Test new user creation
     */
    @Test
    public void testNewUser() {
        User mockUser = ObjectCreator.getUser();

        User user = userService.store(mockUser);

        Assert.assertNotNull(user);
        Assert.assertSame(mockUser.getUsername(), user.getUsername());
        Assert.assertSame(mockUser.getFirstName(), user.getFirstName());
        Assert.assertSame(mockUser.getLastName(), user.getLastName());
        Assert.assertSame(mockUser.getEmail(), user.getEmail());
        Mockito.verify(userRepository, Mockito.times(1));
    }

    /**
     * Test save null user creation
     */
    @Test
    public void testNullUser() {
        User user = userService.store(null);

        Assert.assertNull(user);
    }

    /**
     * Test getting all users
     */
    @Test
    public void testAllUsers() {
        User mockUser = ObjectCreator.getUser();

        List<User> users = userService.findAllUsers();

        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
        User user = users.get(0);
        Assert.assertNotNull(user);
        Assert.assertSame(mockUser.getId(), user.getId());
    }

    /**
     * Test deletion of a user
     */
    @Test
    public void testDeleteUser() {
        userService.delete(ObjectCreator.ID);
    }

    /**
     * Test retrieval of a user
     */
    @Test
    public void testGetUser() {
        User user = userService.findById(ObjectCreator.ID);

        Assert.assertNotNull(user);
    }
}
