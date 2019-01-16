/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * OrikaConfigTests
 *
 * @author brianmichael
 */
public class OrikaConfigTest {

    /**
     * OrikaConfig
     */
    private OrikaConfig orikaConfig;

    /**
     * Test setup
     */
    @Before
    public void setUp() {
        orikaConfig = new OrikaConfig();
    }

    /**
     * Test OrikaMapperFactoryConfig bean
     */
    @Test
    public void orikaMapperFactoryConfigTest() {
        Assert.assertNotNull(
                orikaConfig.orikaMapperFactoryConfig());
    }

}
