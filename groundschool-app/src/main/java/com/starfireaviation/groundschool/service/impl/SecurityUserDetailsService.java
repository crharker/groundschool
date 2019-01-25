/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.SecurityUserDetails;
import com.starfireaviation.groundschool.model.sql.UserEntity;
import com.starfireaviation.groundschool.repository.UserRepository;
import ma.glasnost.orika.MapperFacade;

/**
 * SecurityUserDetailsService
 *
 * @author brianmichael
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    /**
     * UserRepository
     */
    @Autowired
    @Lazy
    private UserRepository userRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user present with username [%s]", username));
        }
        List<String> userRoles = new ArrayList<>();
        userRoles.add(user.getRole());
        return new SecurityUserDetails(mapper.map(user, UserEntity.class), userRoles);
    }

}
