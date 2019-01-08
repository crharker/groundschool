/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.service.SlackService;

/**
 * SlackServiceImpl
 *
 * @author brianmichael
 */
@Service
public class SlackServiceImpl implements SlackService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackServiceImpl.class);

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            String fromAddress,
            String toAddress,
            String body) {
        LOGGER.info(
                String.format(
                        "Sending... fromAddress [%s]; toAddress [%s]; body [%s]",
                        fromAddress,
                        toAddress,
                        body));
    }

}
