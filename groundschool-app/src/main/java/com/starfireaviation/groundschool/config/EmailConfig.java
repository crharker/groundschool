/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.starfireaviation.groundschool.properties.EmailProperties;

/**
 * RestConfig
 *
 * @author brianmichael
 */
@Configuration
@EnableConfigurationProperties({
        EmailProperties.class
})
public class EmailConfig {

    /**
     * Creates a MailSender for sending emails
     *
     * @return MailSender with defaults set
     */
    @Bean
    public JavaMailSender mailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //mailSender.setHost(host);
        return mailSender;
    }

}
