/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.properties.EAA690Properties;
import com.starfireaviation.groundschool.service.ExternalDataRetrievalService;
import com.starfireaviation.groundschool.service.MemberDetailsService;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.util.HtmlResponseParser;

/**
 * UserServiceImpl
 *
 * @author brianmichael
 */
@Service
public class ExternalDataRetrievalServiceImpl implements ExternalDataRetrievalService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalDataRetrievalService.class);

    /**
     * StatisticsService
     */
    @Autowired
    private StatisticService statisticsService;

    /**
     * MemberDetailsService
     */
    @Autowired
    private MemberDetailsService memberDetailsService;

    /**
     * RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * EAA690Properties
     */
    @Autowired
    private EAA690Properties eaa690Properties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void rebuildMemberDetails() {
        Instant start = Instant.now();
        LOGGER.info("Getting EAA690.net login cookie...");
        final String cookie = getEAA690NetLoginCookie();
        LOGGER.info("Received cookie " + cookie);
        final List<MemberDetails> eaa690MemberDetailsList = new ArrayList<>();
        LOGGER.info("Retrieving all EAA690 members...");
        eaa690MemberDetailsList.addAll(getAllEAA690Members(cookie));
        LOGGER.info("Retrieving all EAA690 non-members...");
        eaa690MemberDetailsList.addAll(getAllEAA690NonMembers(cookie));
        for (MemberDetails eaa690MemberDetails : eaa690MemberDetailsList) {
            if (eaa690MemberDetails != null) {
                if (eaa690MemberDetails.getLocalId() != null) {
                    LOGGER.info(
                            "Retrieving EAA690 member details for localId ["
                                    + eaa690MemberDetails.getLocalId()
                                    + "]...");
                    MemberDetails fullDetails = getEAA690MemberDetails(eaa690MemberDetails.getLocalId());
                    fullDetails.setFirstName(eaa690MemberDetails.getFirstName());
                    fullDetails.setLastName(eaa690MemberDetails.getLastName());
                    memberDetailsService.store(fullDetails);
                }
                if (eaa690MemberDetails.getLastName() != null && eaa690MemberDetails.getEaaNumber() != null) {
                    LOGGER.info("Retrieving EAA member info...");
                    memberDetailsService.store(
                            getEAAMemberInfo(eaa690MemberDetails.getLastName(), eaa690MemberDetails.getEaaNumber()));
                    resetEAARESTfulState();
                }
            }
        }
        statisticsService.store(
                new Statistic(
                        StatisticType.REBUILD_MEMBER_DETAILS,
                        String.format("Duration [%s]", Duration.between(start, Instant.now()))));
    }

    /**
     * Resets RESTful state to eaa.org to allow multiple calls
     */
    private void resetEAARESTfulState() {
        // TODO
    }

    /**
     * Makes RESTful call to EAA Headquarters to get member information
     *
     * @param lastName last name
     * @param eaaNumber EAA number
     * @return MemberDetails
     */
    private static MemberDetails getEAAMemberInfo(String lastName, String eaaNumber) {

        //final HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", "application/x-www-form-urlencoded");
        //headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        //headers.add("Accept-Encoding", "gzip, deflate, br");
        //headers.add("Accept-Language", "en-US,en;q=0.9");

        //final HttpEntity<String> entity = new HttpEntity<>(postBody, headers);

        //final ResponseEntity<String> responseEntity =
        //        restTemplate.exchange(
        //                "https://www.eaa.org/apps/chapters/chapterpersonlookup.aspx",
        //                HttpMethod.POST,
        //                entity,
        //                String.class);

        //String response = responseEntity.getBody();
        //System.out.println(response);
        return null;
    }

    /**
     * Makes RESTful call to EAA690.net to get a login cookie
     *
     * @return cookie
     */
    private String getEAA690NetLoginCookie() {
        String cookie = null;
        try {
            final String uriString =
                    "http://eaa690.net/Account/Login?__RequestVerificationToken=aUsmADek-qNafEnIpF2vwnuZv6qdZN7B1XcSluww_rfiphTGgASVObA98ofyPWh-ocMsRlH_0mfrlVGWxzte24H45nSijVAyW1Gvqr2wEKs1&UserName=%s&Password=%s&RememberMe=false";
            final URI builtUri = UriComponentsBuilder
                    .fromHttpUrl(
                            String.format(
                                    uriString,
                                    eaa690Properties.getEaa690NetUsername(),
                                    eaa690Properties.getEaa690NetPassword()))
                    .build()
                    .encode()
                    .toUri();

            final ResponseEntity<String> responseResponseEntity =
                    restTemplate.exchange(builtUri, HttpMethod.POST, null, String.class);
            final HttpHeaders httpHeaders = responseResponseEntity.getHeaders();
            final List<String> setCookieList = httpHeaders.get("Set-Cookie");
            for (String setCookie : setCookieList) {
                if (setCookie != null && setCookie.contains(";")) {
                    cookie = setCookie.substring(0, setCookie.indexOf(";"));
                }
            }
        } catch (HttpClientErrorException e) {
            LOGGER.error("HttpClientErrorException", e);
        }
        return cookie;
    }

    /**
     * Makes RESTful call to EAA690.net to get a list of non-members
     *
     * @param cookie for authentication
     * @return list of MemberDetails for non-members
     */
    private List<MemberDetails> getAllEAA690NonMembers(String cookie) {
        final List<MemberDetails> memberDetailsList = new ArrayList<>();
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.COOKIE, cookie);

            final HttpEntity<String> entityRequest = new HttpEntity<>("", headers);

            int maxPageNumber = 1;
            int pageNumber = 1;
            while (pageNumber <= maxPageNumber) {
                final URI builtUri = UriComponentsBuilder
                        .fromHttpUrl("http://eaa690.net/NonMember?page=" + pageNumber)
                        .build()
                        .encode()
                        .toUri();

                final ResponseEntity<String> responseResponseEntity =
                        restTemplate.exchange(builtUri, HttpMethod.GET, entityRequest, String.class);
                final String responseBody = responseResponseEntity.getBody();
                memberDetailsList.addAll(HtmlResponseParser.parseEAA690NonMembersResponse(responseBody));
                maxPageNumber = HtmlResponseParser.parseMaxPageNumberFromEAA690NetResponse(responseBody);
                pageNumber++;
            }
        } catch (HttpClientErrorException e) {
            LOGGER.error("HttpClientErrorException", e);
        }
        return memberDetailsList;
    }

    /**
     * Makes RESTful call to EAA690.net to get a list of members
     *
     * @param cookie for authentication
     * @return list of MemberDetails for members
     */
    private List<MemberDetails> getAllEAA690Members(String cookie) {
        final List<MemberDetails> memberDetailsList = new ArrayList<>();
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.COOKIE, cookie);

            final HttpEntity<String> entityRequest = new HttpEntity<>("", headers);

            int maxPageNumber = 1;
            int pageNumber = 1;
            while (pageNumber <= maxPageNumber) {
                final URI builtUri = UriComponentsBuilder
                        .fromHttpUrl("http://eaa690.net/Member?page=" + pageNumber)
                        .build()
                        .encode()
                        .toUri();

                final ResponseEntity<String> responseResponseEntity =
                        restTemplate.exchange(builtUri, HttpMethod.GET, entityRequest, String.class);
                final String responseBody = responseResponseEntity.getBody();
                memberDetailsList.addAll(HtmlResponseParser.parseEAA690MembersResponse(responseBody));
                maxPageNumber = HtmlResponseParser.parseMaxPageNumberFromEAA690NetResponse(responseBody);
                pageNumber++;
            }
        } catch (HttpClientErrorException e) {
            LOGGER.error("HttpClientErrorException", e);
        }
        return memberDetailsList;
    }

    /**
     * Retrieve MemberDetails from EAA690.net server
     *
     * @param localId local ID
     * @return memberDetails
     */
    private MemberDetails getEAA690MemberDetails(long localId) {
        MemberDetails memberDetails = null;
        try {
            final URI builtUri = UriComponentsBuilder
                    .fromHttpUrl("http://eaa690.net/Member/Details/" + localId)
                    .build()
                    .encode()
                    .toUri();

            final ResponseEntity<String> responseResponseEntity =
                    restTemplate.exchange(builtUri, HttpMethod.GET, null, String.class);
            memberDetails = HtmlResponseParser.parseEAA690MemberDetailsResponse(responseResponseEntity.getBody());
            memberDetails.setLocalId(localId);
        } catch (HttpClientErrorException e) {
            memberDetails = new MemberDetails();
            LOGGER.error("HttpClientErrorException", e);
        }
        return memberDetails;
    }
}
