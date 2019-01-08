/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * CucumberIntegrationTest
 *
 * @author brianmichael
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty"
        })
public class RunCucumberTest {
    // CucumberIntegrationTest
}
