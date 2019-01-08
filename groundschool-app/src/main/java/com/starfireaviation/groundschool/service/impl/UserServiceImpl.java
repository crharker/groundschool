/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserServiceImpl(UserRepository userRepository, MapperFacade mapperFacade) {
        this.userRepository = userRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public User store(User user) {
        if (user == null) {
            return user;
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

}
