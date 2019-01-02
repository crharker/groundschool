/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User
 *
 * @author brianmichael
 */
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity {

    /**
     * Email
     */
    @Column(name = "email")
    private String email;

    /**
     * Username
     */
    @Column(name = "username")
    private String username;

    /**
     * Password
     */
    @Column(name = "password")
    private String password;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Role
     */
    @Column(name = "role")
    private String role;

    /**
     * Retrieves the value for {@link #email}.
     *
     * @return the current value
     */
    public String getEmail() {
        return email;
    }

    /**
     * Provides a value for {@link #email}.
     *
     * @param email the new value to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the value for {@link #firstName}.
     *
     * @return the current value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Provides a value for {@link #firstName}.
     *
     * @param firstName the new value to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the value for {@link #lastName}.
     *
     * @return the current value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Provides a value for {@link #lastName}.
     *
     * @param lastName the new value to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the value for {@link #username}.
     *
     * @return the current value
     */
    public String getUsername() {
        return username;
    }

    /**
     * Provides a value for {@link #username}.
     *
     * @param username the new value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the value for {@link #password}.
     *
     * @return the current value
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a value for {@link #password}.
     *
     * @param password the new value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the value for {@link #role}.
     *
     * @return the current value
     */
    public String getRole() {
        return role;
    }

    /**
     * Provides a value for {@link #role}.
     *
     * @param role the new value to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
