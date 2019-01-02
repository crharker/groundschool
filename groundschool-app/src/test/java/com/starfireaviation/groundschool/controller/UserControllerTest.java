/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import java.util.List;

import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.ObjectCreator;

/**
 * UserControllerTests
 *
 * @author brianmichael
 */
public class UserControllerTest {

    /**
     * UserController
     */
    private UserController userController;

    /**
     * User
     */
    private User mockUser;

    /**
     * UserService
     */
    @Mock
    private UserService userService;

    /**
     * Test setup
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockUser = ObjectCreator.getUser();

        Mockito.doReturn(ObjectCreator.getUser()).when(userService).store(ArgumentMatchers.any());

        userController = new UserController(userService);
    }

    /**
     * Test the POST endpoint
     */
    @Test
    public void testPost() {
        Mockito.doReturn(mockUser).when(userService).store(ArgumentMatchers.any());

        User user = userController.create(mockUser);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());
        Assert.assertSame(ObjectCreator.ROLE, user.getRole());

        Mockito.verify(userService, times(1)).store(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * Test the PUT endpoint
     */
    @Test
    public void testPut() {
        Mockito.doReturn(mockUser).when(userService).store(ArgumentMatchers.any());

        User user = userController.update(mockUser);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());
        Assert.assertSame(ObjectCreator.ROLE, user.getRole());

        Mockito.verify(userService, times(1)).store(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * Test the GET endpoint
     */
    @Test
    public void testGet() {
        Mockito.doReturn(mockUser).when(userService).findById(ArgumentMatchers.anyLong());

        User user = userController.findOne(ObjectCreator.ID);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());
        Assert.assertSame(ObjectCreator.ROLE, user.getRole());

        Mockito.verify(userService, times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * Test the DELETE endpoint
     */
    @Test
    public void testDelete() {
        Mockito.doReturn(mockUser).when(userService).delete(ArgumentMatchers.anyLong());

        User user = userController.delete(ObjectCreator.ID);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());
        Assert.assertSame(ObjectCreator.ROLE, user.getRole());

        Mockito.verify(userService, times(1)).delete(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * Test the GET all endpoint
     */
    @Test
    public void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);
        Mockito.doReturn(mockUsers).when(userService).findAll();

        List<User> users = userController.findAll();

        Assert.assertNotNull(users);
        Assert.assertTrue("No users found in list", users.size() > 0);
        User user = users.get(0);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());
        Assert.assertSame(ObjectCreator.ROLE, user.getRole());

        Mockito.verify(userService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userService);
    }

}