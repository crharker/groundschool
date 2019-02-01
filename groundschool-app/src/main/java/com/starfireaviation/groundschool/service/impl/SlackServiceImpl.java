/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.ResponseOption;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.SlackMessageEntity;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.repository.SlackMessageRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.ResponseParser;
import com.starfireaviation.groundschool.util.TemplateUtil;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackEventType;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * SlackServiceImpl
 *
 * @author brianmichael
 */
@Service("gsSlackService")
public class SlackServiceImpl implements MessageService, SlackMessagePostedListener {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackServiceImpl.class);

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * QuizService
     */
    @Autowired
    private QuizService quizService;

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * QuestionService
     */
    @Autowired
    private QuestionService questionService;

    /**
     * SMSService
     */
    @Autowired
    @Qualifier("smsService")
    private MessageService smsService;

    /**
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * SlackMessageRepository
     */
    @Autowired
    private SlackMessageRepository slackMessageRepository;

    /**
     * SlackProperties
     */
    @Autowired
    private SlackProperties slackProperties;

    /**
     * FreeMarker Configuration
     */
    @Autowired
    private Configuration freemarkerConfig;

    /**
     * SlackSession
     */
    private SlackSession slackSession = null;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_rsvp.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_RSVP);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_start.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_START);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuestionAskedMsg(final User user, final Question question) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("question.ftl"),
                    TemplateUtil.getModel(user, null, question));
            send(user, null, null, question, message, NotificationEventType.QUESTION_ASKED);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRegisterMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_register.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_REGISTER);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventUnregisterMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_unregister.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_UNREGISTER);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_delete.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_DELETE);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuizCompleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("quiz_complete.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.QUIZ_COMPLETE);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsVerifiedMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_VERIFIED);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsChangeMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_SETTINGS);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendPasswordResetMsg(User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("password_reset.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.PASSWORD_RESET);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendInviteMsg(User user, String destination) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void resendUserSettingsChangeMsg(User user, String response, String originalMessage) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(SMSMessage message) {
        // Not implemented
        return null;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void clearMessageHistory(Long userId) {
        List<SlackMessageEntity> slackMessageEntities = slackMessageRepository.findByUserId(userId);
        for (SlackMessageEntity slackMessageEntity : slackMessageEntities) {
            slackMessageRepository.delete(slackMessageEntity);
        }
    }

    /**
     * Initializes SlackSession
     */
    private void initSlackSession() {
        if (slackSession == null || !slackSession.isConnected()) {
            if (slackSession == null) {
                slackSession = SlackSessionFactory.createWebSocketSlackSession(slackProperties.getToken());
            }
            if (!slackSession.isConnected()) {
                try {
                    slackSession.connect();
                    slackSession.addMessagePostedListener(this);
                } catch (IOException e) {
                    LOGGER.warn("Unable to connect to Slack", e);
                }
            }
        }
    }

    /**
     * Sends message to Slack
     *
     * @param user User
     * @param event Event
     * @param quiz Quiz
     * @param question Question
     * @param message message to be sent
     * @param notificationEventType NotificationEventType
     */
    private void send(
            final User user,
            final Event event,
            final Quiz quiz,
            final Question question,
            final String message,
            NotificationEventType notificationEventType) {
        Instant start = Instant.now();
        initSlackSession();
        final SlackUser slackUser = slackSession.findUserByUserName(user.getSlack());
        slackSession.sendMessageToUser(slackUser, message, null);
        slackMessageRepository.save(
                new SlackMessageEntity(
                        user.getId(),
                        event == null ? null : event.getId(),
                        quiz == null ? null : quiz.getId(),
                        question == null ? null : question.getId(),
                        user.getSlack(),
                        new Date(),
                        message,
                        notificationEventType));
        statisticService.store(
                new Statistic(
                        user.getId(),
                        event == null ? null : event.getId(),
                        quiz == null ? null : quiz.getId(),
                        question == null ? null : question.getId(),
                        StatisticType.SLACK_MESSAGE_SENT,
                        String.format(
                                "Duration [%s]; Message [%s]; Channel [%s]",
                                Duration.between(start, Instant.now()),
                                message,
                                user.getSlack())));
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        // Ignore bot user messages
        if (session.sessionPersona().getId().equals(event.getSender().getId())) {
            return;
        }
        final String channel = event.getChannel().getName();
        final String user = event.getUser().getUserName();
        final String message = event.getMessageContent();
        final SlackEventType eventType = event.getEventType();
        final String sender = event.getSender().getUserName();
        LOGGER.info(
                String.format(
                        "Slack message received: channel [%s]; user [%s]; sender [%s]; type[%s]; message [%s]",
                        channel,
                        user,
                        sender,
                        eventType,
                        message));
        SlackMessageEntity slackMessageEntity = getLastMessageTo(user);
        LOGGER.info(String.format("Last message sent to [%s] was [%s]", user, slackMessageEntity.getMessage()));
        Long userId = null;
        Long eventId = null;
        Long questionId = null;
        try {
            if (slackMessageEntity.isOpen()) {
                userId = slackMessageEntity.getUserId();
                eventId = slackMessageEntity.getEventId();
                questionId = slackMessageEntity.getQuestionId();
                clearMessageHistory(userId);
                boolean success = false;
                switch (slackMessageEntity.getNotificationEventType()) {
                    case USER_SETTINGS:
                        success = processUserSettingsResponse(userId, message);
                        break;
                    case USER_VERIFIED:
                        success = processUserVerifiedResponse(userId, message);
                        break;
                    case USER_DELETE:
                        success = processUserDeletedResponse(userId, message);
                        break;
                    case EVENT_RSVP:
                        success = processEventRSVPResponse(eventId, userId, message);
                        smsService.clearMessageHistory(userId);
                        break;
                    case QUESTION_ASKED:
                        success = processQuestionAskedResponse(questionId, userId, message);
                        smsService.clearMessageHistory(userId);
                        break;
                    default:
                        break;
                }
                if (!success) {
                    notificationService.resend(
                            userId,
                            NotificationType.SLACK,
                            slackMessageEntity.getNotificationEventType(),
                            message,
                            slackMessageEntity.getMessage());
                }
            } else {
                if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
                    handleStop(userId);
                }
            }
        } catch (ResourceNotFoundException e) {
            LOGGER.warn(String.format("No user found for user ID [%s]", userId));
        }
    }

    /**
     * Handles STOP
     *
     * @param userId user ID
     * @throws ResourceNotFoundException when no user is found
     */
    private void handleStop(Long userId) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        user.setSlackEnabled(false);
        userService.store(user);
    }

    /**
     * Process user verified response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserVerifiedResponse(Long userId, String message)
            throws ResourceNotFoundException {
        if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
            handleStop(userId);
        }
        return true;
    }

    /**
     * Process user settings response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserSettingsResponse(Long userId, String message)
            throws ResourceNotFoundException {
        boolean success = true;
        User user = null;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            switch (ResponseParser.determineResponse(message)) {
                case STOP:
                    handleStop(userId);
                    break;
                case CONFIRM:
                    user = userService.findById(userId);
                    user.setSlackVerified(true);
                    userService.store(user);
                    notificationService.send(
                            userId,
                            null,
                            null,
                            NotificationType.SLACK,
                            NotificationEventType.USER_VERIFIED);
                    break;
                case DECLINE:
                    user = userService.findById(userId);
                    user.setSmsEnabled(false);
                    userService.store(user);
                    break;
                default:
                    success = false;
            }
        }
        return success;
    }

    /**
     * Process user deleted response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserDeletedResponse(Long userId, String message)
            throws ResourceNotFoundException {
        if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
            handleStop(userId);
        }
        return true;
    }

    /**
     * Process event RSVP response
     *
     * @param eventId event ID
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processEventRSVPResponse(
            Long eventId,
            Long userId,
            String message) throws ResourceNotFoundException {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            switch (ResponseParser.determineResponse(message)) {
                case STOP:
                    handleStop(userId);
                    break;
                case CONFIRM:
                    eventService.rsvp(eventId, userId, true);
                    break;
                case DECLINE:
                    eventService.rsvp(eventId, userId, false);
                    break;
                default:
                    success = false;
            }
        }
        return success;
    }

    /**
     * Process question asked response
     *
     * @param questionId question ID
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processQuestionAskedResponse(
            Long questionId,
            Long userId,
            String message) throws ResourceNotFoundException {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            ResponseOption responseOption = ResponseParser.determineResponse(message);
            switch (responseOption) {
                case STOP:
                    handleStop(userId);
                    break;
                case A:
                case B:
                case C:
                case D:
                    questionService.answer(questionId, userId, responseOption.toString());
                    Quiz quiz = quizService.getCurrentQuiz();
                    if (quiz != null) {
                        askNextQuestion(userId, eventService.getCurrentEvent(), quiz.getId(), questionId);
                    }
                    break;
                default:
                    success = false;
            }
        }
        return success;
    }

    /**
     * Asks the next question in a quiz, if applicable
     *
     * @param userId User ID
     * @param eventId Event ID
     * @param quizId Quiz ID
     * @param previousQuestionId Question ID
     */
    private void askNextQuestion(Long userId, Long eventId, Long quizId, Long previousQuestionId) {
        final Long nextQuestionId = quizService.getNextQuestion(quizId, previousQuestionId);
        if (nextQuestionId != null) {
            try {
                notificationService.send(
                        userId,
                        eventId,
                        nextQuestionId,
                        NotificationType.ALL,
                        NotificationEventType.QUESTION_ASKED);
            } catch (ResourceNotFoundException e) {
                LOGGER.warn(String.format("Exception %s", e.getMessage()));
            }
        }
    }

    /**
     * Disconnects SlackSession
     */
    public void shutdownSlackSession() {
        if (slackSession != null && slackSession.isConnected()) {
            try {
                slackSession.disconnect();
            } catch (IOException e) {
                LOGGER.warn("Unable to disconnect SlackSession", e);
            }
        }
    }

    /**
     * Retrieves the last message sent to an entity
     *
     * @param to message recipient
     * @return SlackMessageEntity
     */
    private SlackMessageEntity getLastMessageTo(String to) {
        SlackMessageEntity slackMessageEntity = null;
        List<SlackMessageEntity> messages = slackMessageRepository.findByTo(to);
        if (messages != null && messages.size() > 0) {
            TreeMap<Date, SlackMessageEntity> map = new TreeMap<>();
            for (SlackMessageEntity message : messages) {
                map.put(message.getTime(), message);
            }
            Map.Entry<Date, SlackMessageEntity> lastEntry = map.lastEntry();
            slackMessageEntity = lastEntry.getValue();
            for (SlackMessageEntity message : messages) {
                if (!message.getId().equals(slackMessageEntity.getId())) {
                    slackMessageRepository.delete(message);
                }
            }
        }
        return slackMessageEntity;
    }

}
