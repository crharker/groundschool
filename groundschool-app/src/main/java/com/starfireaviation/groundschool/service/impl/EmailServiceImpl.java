/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.EmailService;
import com.starfireaviation.groundschool.service.StatisticService;

/**
 * EmailServiceImpl
 *
 * @author brianmichael
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * MailSender
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            Long userId,
            String fromAddress,
            String toAddress,
            String ccAddress,
            String bccAddress,
            String subject,
            String body,
            boolean html) {
        Instant start = Instant.now();
        LOGGER.info(
                String.format(
                        "Sending... fromAddress [%s]; toAddress [%s]; ccAddress [%s]; bccAddress [%s]; subject [%s]; body [%s]",
                        fromAddress,
                        toAddress,
                        ccAddress,
                        bccAddress,
                        subject,
                        body));
        try {
            final MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg);
            mimeMessageHelper.setTo(toAddress);
            if (ccAddress != null) {
                mimeMessageHelper.setCc(ccAddress);
            }
            if (bccAddress != null) {
                mimeMessageHelper.setBcc(bccAddress);
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, html);
            mailSender.send(msg);
        } catch (MailException | MessagingException ex) {
            LOGGER.error(ex.getMessage());
        }
        Statistic statistic = new Statistic(
                StatisticType.EMAIL_MESSAGE_SENT,
                String.format(
                        "Duration [%s]; Destination [%s]; Subject [%s]; Body [%s]",
                        Duration.between(start, Instant.now()),
                        toAddress,
                        subject,
                        body));
        statistic.setUserId(userId);
        statisticService.store(statistic);
    }

}
