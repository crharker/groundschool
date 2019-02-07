/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.Source;
import com.google.api.services.calendar.model.EventDateTime;
import com.starfireaviation.groundschool.GroundSchoolApplication;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Activity;
import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.AddressService;
import com.starfireaviation.groundschool.service.CalendarService;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.StatisticService;

/**
 * CalendarServiceImpl
 *
 * @author brianmichael
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

    /**
     * SimpleDateFormat
     */
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * JSON_FACTORY
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart. If modifying these scopes, delete your previously
     * saved tokens/ folder.
     */
    //private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * AddressService
     */
    @Autowired
    private AddressService addressService;

    /**
     * LessonPlanService
     */
    @Autowired
    private LessonPlanService lessonPlanService;

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean hasEvent(Long eventId) throws ResourceNotFoundException {
        Instant start = Instant.now();
        boolean found = false;
        com.starfireaviation.groundschool.model.Event event = eventService.get(eventId);
        if (event.getCalendarUrl() != null) {
            found = true;
        }
        statisticService.store(
                new Statistic(
                        null,
                        eventId,
                        null,
                        null,
                        StatisticType.CALENDAR_RETRIEVE_ALL_EVENTS,
                        String.format(
                                "Duration [%s]",
                                Duration.between(start, Instant.now()))));
        return found;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String add(Long eventId) throws ResourceNotFoundException {
        Instant start = Instant.now();
        String eventUrl = null;
        com.starfireaviation.groundschool.model.Event groundSchoolEvent = eventService.get(eventId);
        LessonPlan lessonPlan = lessonPlanService.get(eventId);
        Address address = null;
        try {
            address = addressService.findByEventId(eventId);
        } catch (ResourceNotFoundException e) {
            // Do nothing
        }
        if (groundSchoolEvent != null && lessonPlan != null && address != null) {
            try {
                final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(GroundSchoolApplication.APPLICATION_NAME)
                        .build();
                Event event = new Event()
                        .setSummary(groundSchoolEvent.getTitle())
                        .setLocation(address.toString())
                        .setSource(getSource(groundSchoolEvent))
                        .setVisibility("public")
                        .setDescription(lessonPlan.getSummary());

                event.setStart(
                        new EventDateTime()
                                .setDateTime(new DateTime(sdf.format(groundSchoolEvent.getStartTime())))
                                .setTimeZone("US/Eastern"));

                event.setEnd(
                        new EventDateTime()
                                .setDateTime(computeEventEnd(groundSchoolEvent))
                                .setTimeZone("US/Eastern"));

                String calendarId = "primary";
                event = service.events().insert(calendarId, event).execute();
                eventUrl = event.getHtmlLink();
                LOGGER.info(String.format("Event created: %s", event.getHtmlLink()));
            } catch (IOException | GeneralSecurityException e) {
                LOGGER.error(
                        String.format("There was an error creating the event on the calendar.  %s", e.getMessage()),
                        e);
            }
        }

        statisticService.store(
                new Statistic(
                        null,
                        eventId,
                        null,
                        null,
                        StatisticType.CALENDAR_ADD_EVENT,
                        String.format(
                                "Duration [%s] Event URL [%s]",
                                Duration.between(start, Instant.now()),
                                eventUrl)));
        return eventUrl;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean delete(Long eventId) {
        // TODO Implement this!
        return false;
    }

    /**
     * Builds a Google source for the event
     *
     * @param event Event
     * @return Source
     */
    private static Source getSource(com.starfireaviation.groundschool.model.Event event) {
        Source source = new Source();
        source.setTitle(event.getTitle()); // 
        source.setUrl(/* server URL + */ "/events/" + event.getId());
        return source;
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        //InputStream in = CalendarServiceImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        //new GoogleAuthorizationCodeFlow.Builder(
        //        HTTP_TRANSPORT,
        //        JSON_FACTORY,
        //        clientSecrets,
        //        SCOPES)
        //                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        //                .setAccessType("offline")
        //                .build();
        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return null;
    }

    /**
     * Computes the DateTime for the provided event to end
     *
     * @param event Event
     * @return DateTime
     * @throws ResourceNotFoundException when lesson plan is not found
     */
    private DateTime computeEventEnd(com.starfireaviation.groundschool.model.Event event)
            throws ResourceNotFoundException {
        if (event.getCompletedTime() != null) {
            return new DateTime(sdf.format(event.getCompletedTime()));
        }
        LessonPlan lessonPlan = lessonPlanService.get(event.getLessonPlanId());
        LocalDateTime endTime = event.getStartTime();
        for (Activity activity : lessonPlan.getActivities()) {
            endTime = endTime.plusSeconds(activity.getDuration());
        }
        // TODO set end time to next 30 minute boundary
        return new DateTime(sdf.format(endTime));
    }

}
