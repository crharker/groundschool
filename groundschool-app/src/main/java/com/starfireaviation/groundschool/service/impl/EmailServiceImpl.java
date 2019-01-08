/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.starfireaviation.groundschool.service.EmailService;

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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            String fromAddress,
            String toAddress,
            String ccAddress,
            String bccAddress,
            String subject,
            String body,
            boolean html) {
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
    }

}
