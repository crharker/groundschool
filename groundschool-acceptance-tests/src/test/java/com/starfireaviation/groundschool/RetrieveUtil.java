/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RetrieveUtil
 *
 * @author brianmichael
 */
public class RetrieveUtil {

    /**
     * Retrieve resource from response
     *
     * @param <T> class type
     * @param response HttpResponse
     * @param clazz class
     * @return response
     * @throws IOException when things go wrong
     */
    public static <T> T retrieveResourceFromResponse(final HttpResponse response, final Class<T> clazz)
            throws IOException {
        final String jsonFromResponse = EntityUtils.toString(response.getEntity());
        final ObjectMapper mapper = new ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

}
