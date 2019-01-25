/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.slack;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import com.starfireaviation.groundschool.properties.SlackProperties;

import java.util.regex.Matcher;

/**
 * SlackBot
 *
 * @author brianmichael
 */
@JBot
public class SlackBot extends Bot {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackBot.class);

    /**
     * SlackProperties
     */
    @Autowired
    private SlackProperties slackProperties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String getSlackToken() {
        return slackProperties.getToken();
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Bot getSlackBot() {
        return this;
    }

    /**
     * Invoked when the bot receives a direct mention (@botname: message) or a direct message. NOTE: These two event
     * types are added by jbot to make your task easier, Slack doesn't have any direct way to determine these type of
     * events.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(events = {
            EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE
    })
    public void onReceiveDM(WebSocketSession session, Event event) {
        reply(session, event, "Hi, I am " + slackService.getCurrentUser().getName());
    }

    /**
     * Invoked when bot receives an event of type message with text satisfying the pattern {@code ([a-z ]{2})(\d+)([a-z
     * ]{2})}. For example, messages like "ab12xy" or "ab2bc" etc will invoke this method.
     *
     * @param session WebSocketSession
     * @param event Event
     * @param matcher Matcher
     */
    @Controller(events = EventType.MESSAGE, pattern = "^([a-z ]{2})(\\d+)([a-z ]{2})$")
    public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
        reply(
                session,
                event,
                "First group: "
                        + matcher.group(0)
                        + "\n"
                        +
                        "Second group: "
                        + matcher.group(1)
                        + "\n"
                        +
                        "Third group: "
                        + matcher.group(2)
                        + "\n"
                        +
                        "Fourth group: "
                        + matcher.group(3));
    }

    /**
     * Invoked when an item is pinned in the channel.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(events = EventType.PIN_ADDED)
    public void onPinAdded(WebSocketSession session, Event event) {
        reply(session, event, "Thanks for the pin! You can find all pinned items under channel details.");
    }

    /**
     * Invoked when bot receives an event of type file shared.
     *
     * NOTE: You can't reply to this event as slack doesn't send a channel id for this event type. You can learn more
     * about <a href="https://api.slack.com/events/file_shared">file_shared</a> event from Slack's Api documentation.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(events = EventType.FILE_SHARED)
    public void onFileShared(WebSocketSession session, Event event) {
        LOGGER.info("File shared: {}", event);
    }

    /**
     * Conversation feature of JBot.
     *
     * This method is the starting point of the conversation (as it calls {@link Bot#startConversation(Event, String)}
     * within it. You can chain methods which will be invoked one after the other leading to a conversation. You can
     * chain methods with {@link Controller#next()} by specifying the method name to chain with.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(pattern = "(setup meeting)", next = "confirmTiming")
    public void setupMeeting(WebSocketSession session, Event event) {
        startConversation(event, "confirmTiming");   // start conversation
        reply(session, event, "Cool! At what time (ex. 15:30) do you want me to set up the meeting?");
    }

    /**
     * This method will be invoked after {@link SlackBot#setupMeeting(WebSocketSession, Event)}.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(next = "askTimeForMeeting")
    public void confirmTiming(WebSocketSession session, Event event) {
        reply(
                session,
                event,
                "Your meeting is set at "
                        + event.getText()
                        +
                        ". Would you like to repeat it tomorrow?");
        nextConversation(event);    // jump to next question in conversation
    }

    /**
     * This method will be invoked after {@link SlackBot#confirmTiming(WebSocketSession, Event)}.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller(next = "askWhetherToRepeat")
    public void askTimeForMeeting(WebSocketSession session, Event event) {
        if (event.getText().contains("yes")) {
            reply(session, event, "Okay. Would you like me to set a reminder for you?");
            nextConversation(event);    // jump to next question in conversation  
        } else {
            reply(session, event, "No problem. You can always schedule one with 'setup meeting' command.");
            stopConversation(event);    // stop conversation only if user says no
        }
    }

    /**
     * This method will be invoked after {@link SlackBot#askTimeForMeeting(WebSocketSession, Event)}.
     *
     * @param session WebSocketSession
     * @param event Event
     */
    @Controller
    public void askWhetherToRepeat(WebSocketSession session, Event event) {
        if (event.getText().contains("yes")) {
            reply(session, event, "Great! I will remind you tomorrow before the meeting.");
        } else {
            reply(session, event, "Okay, don't forget to attend the meeting tomorrow :)");
        }
        stopConversation(event);    // stop conversation
    }
}
