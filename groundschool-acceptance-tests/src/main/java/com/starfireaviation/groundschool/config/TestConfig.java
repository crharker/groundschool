/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.starfireaviation.groundschool.constants.ServiceTestProperties;
import com.starfireaviation.groundschool.controller.UserController;

/**
 * TestConfig
 *
 * @author brianmichael
 */
@Configuration
@EnableAutoConfiguration
@Import({
        AcceptanceTestConfig.class,
        LocalTestConfig.class
})
@ComponentScan(value = "com.starfireaviation.groundschool")
@EnableConfigurationProperties(ServiceTestProperties.class)
public class TestConfig {

    /**
     * Creates a map of header values for service requests.
     *
     * @return Map of configured header values
     */
    public static MultivaluedMap<String, String> getHeaders() {
        final MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();

        return headers;
    }

    /**
     * UsersService
     *
     * @param serviceTestProperties ServiceTestProperties
     * @return UsersService
     */
    @Bean
    public UserController usersService(final ServiceTestProperties serviceTestProperties) {
        return null; //serviceFactory.create(UsersService.class, getHeaders(), serviceTestProperties.getUri());
    }

}
