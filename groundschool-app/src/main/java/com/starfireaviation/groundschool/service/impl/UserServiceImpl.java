/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.UserEntity;
import com.starfireaviation.groundschool.repository.UserRepository;
import com.starfireaviation.groundschool.service.UserService;

import ma.glasnost.orika.MapperFacade;

/**
 * UserServiceImpl
 *
 * @author brianmichael
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>UserServiceImpl</code> with the default data.
     */
    public UserServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserServiceImpl</code> with the default data.
     *
     * @param userRepository UserRepository
     * @param mapperFacade MapperFacade
     */
    public UserServiceImpl(
            UserRepository userRepository,
            MapperFacade mapperFacade) {
        this.userRepository = userRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public User store(User user) throws ResourceNotFoundException {
        if (user == null) {
            return user;
        }
        final Long userId = user.getId();
        if (userId != null) {
            final User existingUser = findById(userId);
            if (existingUser == null) {
                final String msg = String.format("No user found for ID [%s]", userId);
                LOGGER.warn(msg);
                throw new ResourceNotFoundException(msg);
            }
            user.setPassword(existingUser.getPassword());
        }
        if (user.getRole() == null) {
            LOGGER.info(String.format("store() Setting role to %s", Role.STUDENT));
            user.setRole(Role.STUDENT);
        }
        return mapper.map(userRepository.save(mapper.map(user, UserEntity.class)), User.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public User delete(long id) {
        final User user = mapper.map(findById(id), User.class);
        if (user != null) {
            userRepository.delete(mapper.map(user, UserEntity.class));
        }
        return user;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<User> findAllUsers() {
        final List<User> users = new ArrayList<>();
        final List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            users.add(mapper.map(userEntity, User.class));
        }
        return users;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public User findById(long id) {
        return mapper.map(userRepository.findById(id), User.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public User findByUsername(String username) {
        return mapper.map(userRepository.findByUsername(username), User.class);
    }

}
