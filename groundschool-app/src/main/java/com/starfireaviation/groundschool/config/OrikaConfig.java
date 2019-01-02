/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.springframework.context.annotation.Bean;

/**
 * OrikaConfig
 *
 * @author brianmichael
 */
public class OrikaConfig {

    /**
     * Creates an Orika mapper factory configuration
     *
     * @return OrikaMapperFactoryConfig
     */
    @Bean
    public OrikaMapperFactoryConfig orikaMapperFactoryConfig() {
        return new OrikaMapperFactoryConfig();
    }

}
