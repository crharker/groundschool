/*
 * -----------------------------------------------------------------------------
 *   Copyright 2018 Starfire Aviation, LLC
 * -----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.starfireaviation.groundschool.utils.TestMode;
import com.starfireaviation.groundschool.constants.ServiceTestProperties;

/**
 * Local test configuration
 *
 * @author brianmichael
 */
@Configuration
@Profile(TestMode.LOCAL_TEST_PROFILE)
@EnableConfigurationProperties(ServiceTestProperties.class)
public class LocalTestConfig {
    // LocalTestConfig
}
