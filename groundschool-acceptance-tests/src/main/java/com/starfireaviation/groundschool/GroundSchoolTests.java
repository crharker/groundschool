/*
 * -----------------------------------------------------------------------------
 *   Copyright 2018 Starfire Aviation, LLC
 * -----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

/**
 * GroundSchoolTests
 *
 * @author brianmichael
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/acceptance-main-report"
        },
        glue = {
                "com.starfireaviation.groundschool"
        }, strict = true)
public class GroundSchoolTests {
    // GroundSchoolTests
}
