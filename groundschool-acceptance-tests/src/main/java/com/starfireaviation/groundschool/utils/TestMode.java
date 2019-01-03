/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.utils;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

/**
 * Utility that provides constants and methods for managing the current test mode.
 *
 * @author brianmichael
 */
public class TestMode {

    /**
     * Acceptance testing profile
     */
    public static final String ACCEPTANCE_TEST_PROFILE = "acceptance-test";

    /**
     * Local testing mode profile
     */
    public static final String LOCAL_TEST_PROFILE = "local-test";

    /**
     * Name of property used to control mode of test.
     */
    private static final String MODE_PROPERTY = "nep.test.mode";

    /**
     * The current test environment.
     */
    private final Environment environment;

    /**
     * Initializes an instance of <code>TestMode</code> with the default data.
     *
     * @param environment Environment
     */
    public TestMode(Environment environment) {
        this.environment = environment;
    }

    /**
     * Determines if the current test is in unit mode.
     *
     * @return <code>true</code> if in unit mode.
     */
    public boolean isUnitMode() {
        return !isIntegrationMode();
    }

    /**
     * Determines if the current test is in local mode.
     *
     * @return <code>true</code> if in local mode.
     */
    public boolean isLocalMode() {
        return isIntegrationMode()
                &&
                (TestModeType.LOCAL == getModeFromProperty()
                        ||
                        environment.acceptsProfiles(Profiles.of(LOCAL_TEST_PROFILE)));
    }

    /**
     * Determines if the current test is in acceptance mode.
     *
     * @return <code>true</code> if in acceptance mode.
     */
    public boolean isAcceptanceMode() {
        return isIntegrationMode()
                &&
                (TestModeType.ACCEPTANCE == getModeFromProperty()
                        || environment.acceptsProfiles(Profiles.of(ACCEPTANCE_TEST_PROFILE)));
    }

    /**
     * Determines if the current test is in integration mode (either acceptance or local test).
     *
     * @return <code>true</code> if in integration mode.
     */
    public boolean isIntegrationMode() {
        return TestModeType.UNIT != getModeFromProperty()
                || environment.acceptsProfiles(Profiles.of(LOCAL_TEST_PROFILE, ACCEPTANCE_TEST_PROFILE));
    }

    /**
     * Gets the current mode the tests are in from properties (if profiles are not used).
     *
     * @return the test mode
     */
    private TestModeType getModeFromProperty() {
        final String mode = environment.getProperty(MODE_PROPERTY);
        return StringUtils.isNotEmpty(mode)
                ? Enum.valueOf(TestModeType.class, mode.toUpperCase(Locale.ROOT))
                : TestModeType.UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (isUnitMode()) {
            return TestModeType.UNIT.toString();
        } else if (isLocalMode()) {
            return TestModeType.LOCAL.toString();
        } else if (isIntegrationMode()) {
            return TestModeType.INTEGRATION.toString();
        } else {
            return TestModeType.ACCEPTANCE.toString();
        }
    }
}
