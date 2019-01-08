/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import java.util.Properties;

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
     * @param emailProperties EmailProperties
     * @return MailSender with defaults set
     */
    @Bean
    public JavaMailSender mailSender(EmailProperties emailProperties) {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());
        Properties javaMailProperties = new Properties();

        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.required", "true");
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.connectiontimeout", "5000");
        javaMailProperties.setProperty("mail.smtp.timeout", "3000");
        javaMailProperties.setProperty("mail.smtp.writetimeout", "5000");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

}
