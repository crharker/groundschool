/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import java.time.Duration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.starfireaviation.groundschool.properties.EAA690Properties;
import com.starfireaviation.groundschool.properties.TimeoutProperties;

/**
 * RestConfig
 *
 * @author brianmichael
 */
@Configuration
@EnableConfigurationProperties({
        TimeoutProperties.class,
        EAA690Properties.class
})
public class RestConfig {

    /**
     * Creates a rest template with default timeout settings. The bean definition will be updated to accept timeout
     * parameters once those are part of the Customer settings.
     *
     * @param restTemplateBuilder RestTemplateBuilder
     * @param timeoutProperties TimeoutProperties
     *
     * @return Rest Template with request, read, and connection timeouts set
     */
    @Bean
    public RestTemplate restTemplate(
            RestTemplateBuilder restTemplateBuilder,
            TimeoutProperties timeoutProperties) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(timeoutProperties.getConnect()))
                .setReadTimeout(Duration.ofMillis(timeoutProperties.getRead()))
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
}
