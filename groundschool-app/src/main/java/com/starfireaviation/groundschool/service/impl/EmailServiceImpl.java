/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            String fromAddress,
            String toAddress,
            String ccAddress,
            String bccAddress,
            String subject,
            String body) {
        // TODO Auto-generated method stub
    }

}
