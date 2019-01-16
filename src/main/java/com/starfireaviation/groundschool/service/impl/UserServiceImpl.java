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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
     * BCryptPasswordEncoder
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
     * @param bCryptPasswordEncoder BCryptPasswordEncoder
     */
    public UserServiceImpl(
            UserRepository userRepository,
            MapperFacade mapperFacade,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        LOGGER.info(String.format("store() looking for UserEntity with username %s", user.getUsername()));
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity == null) {
            LOGGER.info("store() UserEntity is null");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else if (!bCryptPasswordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            LOGGER.info(
                    String.format(
                            "store() UserEntity is not null, and password [%s] does not match stored password [%s]",
                            user.getPassword(),
                            userEntity.getPassword()));
            user.setId(userEntity.getId());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            LOGGER.info(
                    String.format(
                            "store() UserEntity is not null, setting ID to [%s] before storing",
                            userEntity.getId()));
            user.setId(userEntity.getId());
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

}
