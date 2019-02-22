/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.starfireaviation.groundschool.model.sql.UserEntity;

/**
 * SecurityUserDetails
 *
 * @author brianmichael
 */
public class SecurityUserDetails extends UserEntity implements UserDetails {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * User Roles
     */
    private List<String> userRoles;

    /**
     * Token
     */
    private String token;

    /**
     * Initializes an instance of <code>SecurityUserDetails</code> with the default data.
     */
    public SecurityUserDetails() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>SecurityUserDetails</code> with the default data.
     *
     * @param user User
     * @param userRoles List of user roles
     */
    public SecurityUserDetails(UserEntity user, List<String> userRoles) {
        super(user);
        this.userRoles = userRoles;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Retrieves the value for {@link #userRoles}.
     *
     * @return the current value
     */
    public List<String> getUserRoles() {
        return userRoles;
    }

    /**
     * Provides a value for {@link #userRoles}.
     *
     * @param userRoles the new value to set
     */
    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * Retrieves the value for {@link #token}.
     *
     * @return the current value
     */
    public String getToken() {
        return token;
    }

    /**
     * Provides a value for {@link #token}.
     *
     * @param token the new value to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
