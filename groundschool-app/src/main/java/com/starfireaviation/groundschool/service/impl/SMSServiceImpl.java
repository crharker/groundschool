/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.service.SMSService;

/**
 * SMSServiceImpl
 *
 * @author brianmichael
 */
@Service
public class SMSServiceImpl implements SMSService {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            String fromAddress,
            String toAddress,
            String body) {
        // TODO Auto-generated method stub

    }

}
