/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.jbehave;

/**
 * GithubUserResponsePayloadStoryLiveTest
 *
 * @author brianmichael
 */
public class GithubUserResponsePayloadStoryLiveTest extends AbstractStory {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    String storyName() {
        return "github_user_response_payload.story";
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    Object stepInstance() {
        return new GithubUserResponsePayloadSteps();
    }

}
