/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.List;

/**
 * Quiz
 *
 * @author brianmichael
 */
public class Quiz extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * Questions
     */
    private List<Question> questions;

    /**
     * Retrieves the value for {@link #title}.
     *
     * @return the current value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides a value for {@link #title}.
     *
     * @param title the new value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the value for {@link #questions}.
     *
     * @return the current value
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Provides a value for {@link #questions}.
     *
     * @param questions the new value to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
