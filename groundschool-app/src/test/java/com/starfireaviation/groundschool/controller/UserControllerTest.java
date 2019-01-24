/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import java.util.List;

import javax.management.remote.JMXPrincipal;

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
import com.starfireaviation.groundschool.service.NotificationService;
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
     * NotificationService
     */
    @Mock
    private NotificationService notificationService;

    /**
     * Test setup
     *
     * @throws Exception when things go wrong
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockUser = ObjectCreator.getUser();

        Mockito.doReturn(ObjectCreator.getUser()).when(userService).store(ArgumentMatchers.any());
        Mockito.doNothing().when(notificationService).send(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any());

        userController = new UserController(userService, notificationService);
    }

    /**
     * Test the POST endpoint
     *
     * @throws Exception when things go wrong
     */
    @Test
    public void testPost() throws Exception {
        Mockito.doReturn(mockUser).when(userService).store(ArgumentMatchers.any());

        User user = userController.post(mockUser);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());

        Mockito.verify(userService, times(1)).store(ArgumentMatchers.any());
        Mockito.verify(notificationService, times(1)).send(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
        Mockito.verifyNoMoreInteractions(notificationService);
    }

    /**
     * Test the PUT endpoint
     *
     * @throws Exception when things go wrong
     */
    @Test
    public void testPut() throws Exception {
        Mockito.doReturn(mockUser).when(userService).store(ArgumentMatchers.any());

        User user = userController.put(mockUser, new JMXPrincipal("admin"));

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());

        Mockito.verify(userService, times(1)).store(ArgumentMatchers.any());
        Mockito.verify(notificationService, times(1)).send(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
        Mockito.verifyNoMoreInteractions(notificationService);
    }

    /**
     * Test the GET endpoint
     */
    @Test
    public void testGet() {
        Mockito.doReturn(mockUser).when(userService).findById(ArgumentMatchers.anyLong());

        User user = userController.get(ObjectCreator.ID, new JMXPrincipal("admin"));

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());

        Mockito.verify(userService, times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * Test the DELETE endpoint
     *
     * @throws Exception when things go wrong
     */
    @Test
    public void testDelete() throws Exception {
        Mockito.doReturn(mockUser).when(userService).delete(ArgumentMatchers.anyLong());

        User user = userController.delete(ObjectCreator.ID, new JMXPrincipal("admin"));

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertSame(ObjectCreator.ID, user.getId());
        Assert.assertSame(ObjectCreator.PASSWORD, user.getPassword());
        Assert.assertSame(ObjectCreator.USERNAME, user.getUsername());
        Assert.assertSame(ObjectCreator.FIRST_NAME, user.getFirstName());
        Assert.assertSame(ObjectCreator.LAST_NAME, user.getLastName());
        Assert.assertSame(ObjectCreator.EMAIL, user.getEmail());

        Mockito.verify(userService, times(1)).delete(ArgumentMatchers.anyLong());
        Mockito.verify(notificationService, times(1)).send(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
        Mockito.verifyNoMoreInteractions(notificationService);
    }

    /**
     * Test the GET all endpoint
     */
    @Test
    public void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);
        Mockito.doReturn(mockUsers).when(userService).findAllUsers();

        List<User> users = userController.list(new JMXPrincipal("admin"));

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

        Mockito.verify(userService, times(1)).findAllUsers();
        Mockito.verifyNoMoreInteractions(userService);
    }

}
