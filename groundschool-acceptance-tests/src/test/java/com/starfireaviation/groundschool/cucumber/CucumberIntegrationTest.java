/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * CucumberIntegrationTest
 *
 * @author brianmichael
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:Feature")
public class CucumberIntegrationTest {
    // CucumberIntegrationTest
}
