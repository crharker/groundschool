/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

/**
 * Answer
 *
 * @author brianmichael
 */
public class Answer extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Text
     */
    private String text;

    /**
     * Choice
     */
    private String choice;

    /**
     * Correct
     */
    private boolean correct;

    /**
     * Discussion
     */
    private String discussion;

    /**
     * Retrieves the value for {@link #text}.
     *
     * @return the current value
     */
    public String getText() {
        return text;
    }

    /**
     * Provides a value for {@link #text}.
     *
     * @param text the new value to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retrieves the value for {@link #correct}.
     *
     * @return the current value
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Provides a value for {@link #correct}.
     *
     * @param correct the new value to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * Retrieves the value for {@link #discussion}.
     *
     * @return the current value
     */
    public String getDiscussion() {
        return discussion;
    }

    /**
     * Provides a value for {@link #discussion}.
     *
     * @param discussion the new value to set
     */
    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    /**
     * Retrieves the value for {@link #choice}.
     *
     * @return the current value
     */
    public String getChoice() {
        return choice;
    }

    /**
     * Provides a value for {@link #choice}.
     *
     * @param choice the new value to set
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

}
