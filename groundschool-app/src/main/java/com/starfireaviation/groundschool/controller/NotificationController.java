/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starfireaviation.groundschool.model.Message;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.MessageService;

import me.ramswaroop.jbot.core.slack.models.Attachment;
import me.ramswaroop.jbot.core.slack.models.RichMessage;

/**
 * NotificationController
 *
 * @author brianmichael
 */
@RestController
@RequestMapping({
        "/notifications"
})
public class NotificationController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    /**
     * SMSService
     */
    @Autowired
    @Qualifier("smsService")
    private MessageService smsService;

    /**
     * SlackProperties
     */
    @Autowired
    private SlackProperties slackProperties;

    /**
     * Receives a SMS message
     *
     * @param message received
     * @return response
     */
    @PostMapping(path = {
            "/sms"
    })
    public String sms(@RequestBody String message) {
        LOGGER.info(String.format("sms() called with [%s]", message));
        return smsService.receiveMessage(new Message(message));
    }

    /**
     * Slash Command handler. When a user types for example "/app help" then slack sends a POST request to this
     * endpoint. So, this endpoint should match the url you set while creating the Slack Slash Command.
     *
     * @param token token
     * @param teamId team ID
     * @param teamDomain team domain
     * @param channelId channel ID
     * @param channelName channel name
     * @param userId user ID
     * @param userName user name
     * @param command command
     * @param text text
     * @param responseUrl response URL
     * @return RichMessage
     */
    @RequestMapping(value = "/slack",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RichMessage onReceiveSlashCommand(
            @RequestParam("token") String token,
            @RequestParam("team_id") String teamId,
            @RequestParam("team_domain") String teamDomain,
            @RequestParam("channel_id") String channelId,
            @RequestParam("channel_name") String channelName,
            @RequestParam("user_id") String userId,
            @RequestParam("user_name") String userName,
            @RequestParam("command") String command,
            @RequestParam("text") String text,
            @RequestParam("response_url") String responseUrl) {
        // validate token
        if (!token.equals(slackProperties.getToken())) {
            return new RichMessage("Sorry! You're not lucky enough to use our slack command.");
        }

        /** build response */
        RichMessage richMessage = new RichMessage("The is Slash Commander!");
        richMessage.setResponseType("in_channel");
        // set attachments
        Attachment[] attachments = new Attachment[1];
        attachments[0] = new Attachment();
        attachments[0].setText("I will perform all tasks for you.");
        richMessage.setAttachments(attachments);

        // For debugging purpose only
        if (LOGGER.isDebugEnabled()) {
            try {
                LOGGER.debug("Reply (RichMessage): {}", new ObjectMapper().writeValueAsString(richMessage));
            } catch (JsonProcessingException e) {
                LOGGER.debug("Error parsing RichMessage: ", e);
            }
        }

        return richMessage.encodedMessage(); // don't forget to send the encoded message to Slack
    }
}
