/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

/**
 * GitHubUser
 *
 * @author brianmichael
 */
public class GitHubUser {

    /**
     * Login
     */
    private String login;

    /**
     * Initializes an instance of <code>GitHubUser</code> with the default data.
     */
    public GitHubUser() {
        super();
    }

    /**
     * Retrieves the value for {@link #login}.
     *
     * @return the current value
     */
    public String getLogin() {
        return login;
    }

    /**
     * Provides a value for {@link #login}.
     *
     * @param login the new value to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

}
