/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

/**
 * GithubUserResponseMediaTypeStoryLiveTest
 *
 * @author brianmichael
 */
public class GithubUserResponseMediaTypeStoryLiveTest extends AbstractStory {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    String storyName() {
        return "github_user_response_mediatype.story";
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    Object stepInstance() {
        return new GithubUserResponseMediaTypeSteps();
    }

}
