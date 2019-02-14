/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JWTAuthorizationFilter
 *
 * @author brianmichael
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Initializes an instance of <code>JWTAuthorizationFilter</code> with the default data.
     *
     * @param authManager AuthenticationManager
     */
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(JWTAuthenticationFilter.HEADER_STRING);

        if (header == null || !header.startsWith(JWTAuthenticationFilter.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * Get authentication
     *
     * @param request HttpServletRequest
     * @return UsernamePasswordAuthenticationToken
     */
    private static UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTAuthenticationFilter.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts
                    .parser()
                    .setSigningKey(JWTAuthenticationFilter.SECRET.getBytes())
                    .parseClaimsJws(token.replace(JWTAuthenticationFilter.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
