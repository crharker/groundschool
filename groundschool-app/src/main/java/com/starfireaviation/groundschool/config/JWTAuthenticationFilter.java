/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starfireaviation.groundschool.model.SecurityUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * JWTAuthenticationFilter
 *
 * @author brianmichael
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * SECRET
     */
    public static final String SECRET = "SecretKeyToGenJWTs";

    /**
     * EXPIRATION_TIME
     */
    public static final long EXPIRATION_TIME = 86400000;

    /**
     * TOKEN_PREFIX
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * HEADER_STRING
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";

    /**
     * AuthenticationManager
     */
    private AuthenticationManager authenticationManager;

    /**
     * Initializes an instance of <code>JWTAuthenticationFilter</code> with the default data.
     *
     * @param authenticationManager AuthenticationManager
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
        try {
            SecurityUserDetails creds = new ObjectMapper()
                    .readValue(req.getInputStream(), SecurityUserDetails.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) {

        final SecurityUserDetails user = (SecurityUserDetails) auth.getPrincipal();
        res.addHeader(
                HEADER_STRING,
                TOKEN_PREFIX + " " + generateToken(user.getUsername(), user.getId()));
    }

    /**
     * Generates a JWT token
     *
     * @param username username
     * @param userId Long
     * @return token
     */
    private static String generateToken(String username, Long userId) {
        return Jwts
                .builder()
                .setSubject(username)
                .claim(USER_ID, userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }

}
