/*
 * -----------------------------------------------------------------------------
 *   Copyright 2018 Starfire Aviation, LLC
 * -----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import org.junit.internal.TextListener;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * TestApplication
 *
 * @author brianmichael
 */
public final class TestApplication {

    /**
     * Entry point for Acceptance Tests main application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String... args) {
        System.exit(run());
    }

    /**
     * Non-static entry point.
     *
     * @return Number of failed tests.
     */
    private static int run() {
        final JUnitCore junit = new JUnitCore();

        final TextListener textListener = new TextListener(System.out);
        junit.addListener(textListener);

        final Computer computer = new Computer();
        final Result junitResult = junit.run(computer, GroundSchoolTests.class);

        return junitResult.getFailureCount();
    }

    /**
     * Constructor.
     */
    private TestApplication() {
    }
}
