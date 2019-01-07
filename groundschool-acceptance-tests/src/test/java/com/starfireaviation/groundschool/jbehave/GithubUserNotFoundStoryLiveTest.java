/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

/**
 * GithubUserNotFoundStoryLiveTest
 *
 * @author brianmichael
 */
public class GithubUserNotFoundStoryLiveTest extends AbstractStory {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    String storyName() {
        return "github_user_not_found.story";
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    Object stepInstance() {
        return new GithubUserNotFoundSteps();
    }

}
