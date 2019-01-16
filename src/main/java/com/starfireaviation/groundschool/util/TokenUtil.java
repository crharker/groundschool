/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.security.Key;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

/**
 * TokenUtil
 *
 * @author brianmichael
 */
public class TokenUtil {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * Gets a JWT token
     *
     * @param url host Ex. "http://groundschool.starfireaviation.com"
     * @param username User's "username"
     * @param userId User ID
     * @param expirationDate Date
     * @return token
     */
    public static String getToken(String url, String username, String userId, Date expirationDate) {
        if (url == null || username == null || userId == null || expirationDate == null) {
            return null;
        }
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //TODO byte[] key = getSignatureKey();
        String jwt =
                Jwts
                        .builder()
                        .setIssuedAt(new Date())
                        .setIssuer(url)
                        .setSubject(username)
                        .setId(userId)
                        .setExpiration(expirationDate)
                        .signWith(SignatureAlgorithm.HS256, key)
                        .compact();
        return jwt;
    }

    /**
     * Gets claims set in provided token
     *
     * @param token JWT
     * @return map of claims
     * @throws SignatureException when an invalid token is received
     */
    public static Claims getClaims(String token) throws SignatureException {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //TODO byte[] key = getSignatureKey();
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (SignatureException se) {
            LOGGER.warn(String.format("Invalid token [%s] provided.  Msg: %s", token, se.getMessage()));
            throw se;
        }
    }
}
