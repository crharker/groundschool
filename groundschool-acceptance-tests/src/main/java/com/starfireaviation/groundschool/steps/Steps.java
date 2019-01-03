/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for step implementations. All step implementations need to derive from this base class in order to ensure
 * steps use a consistently configured application context.
 *
 * @author brianmichael
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = SpringBootContextLoader.class,
        locations = "classpath:cucumber.xml")
public class Steps {
    // Steps
}
