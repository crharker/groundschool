/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

/**
 * AbstractStory
 *
 * @author brianmichael
 */
public abstract class AbstractStory extends JUnitStories {

    /**
     * StoryName
     *
     * @return story name
     */
    abstract String storyName();

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                                .withFormats(Format.CONSOLE));
    }

    /**
     * StepInstance
     *
     * @return instance
     */
    abstract Object stepInstance();

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepInstance());
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(storyName());
    }

}
