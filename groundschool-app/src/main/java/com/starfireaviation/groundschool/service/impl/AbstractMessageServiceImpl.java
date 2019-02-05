/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.ResponseOption;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.MessageEntity;
import com.starfireaviation.groundschool.repository.MessageRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.ResponseParser;

/**
 * AbstractMessageServiceImpl
 *
 * @author brianmichael
 */
public abstract class AbstractMessageServiceImpl implements MessageService {

    /**
     * Logger
     */
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * TN_PATTERN
     */
    private static final Pattern TN_PATTERN = Pattern.compile(".*\\+1(\\d{10}).*");

    /**
     * UserService
     */
    @Autowired
    protected UserService userService;

    /**
     * EventService
     */
    @Autowired
    protected EventService eventService;

    /**
     * QuizService
     */
    @Autowired
    protected QuizService quizService;

    /**
     * NotificationService
     */
    @Autowired
    protected NotificationService notificationService;

    /**
     * QuestionService
     */
    @Autowired
    protected QuestionService questionService;

    /**
     * SMSMessageRepository
     */
    @Autowired
    protected MessageRepository messageRepository;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuizCompleteMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuestionAskedMsg(final User user, final Question question) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRegisterMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventUnregisterMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsVerifiedMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsChangeMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendPasswordResetMsg(User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void resendUserSettingsChangeMsg(
            final User user,
            String response,
            String originalMessage) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(SMSMessage message) {
        String response = null;
        // Not implemented
        return response;
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
    public void clearMessageHistory(Long userId) {
        List<MessageEntity> messageEntities = messageRepository.findByUserId(userId);
        for (MessageEntity messageEntity : messageEntities) {
            messageRepository.delete(messageEntity);
        }
    }

    /**
     * Process user response
     *
     * @param message received from user
     * @param to user
     * @param notificationType NotificationType
     */
    protected void processUserResponse(
            final String to,
            final String message,
            final NotificationType notificationType) {
        Long eventId;
        Long questionId;
        final MessageEntity messageEntity = getLastMessage(to);
        if (messageEntity != null) {
            Long userId = messageEntity.getUserId();
            try {
                if (messageEntity.isOpen()) {
                    LOGGER.info(
                            String.format("Last message sent to [%s] was [%s]", userId, messageEntity.getMessage()));
                    eventId = messageEntity.getEventId();
                    questionId = messageEntity.getQuestionId();
                    boolean success = false;
                    switch (messageEntity.getNotificationEventType()) {
                        case USER_SETTINGS:
                            success = processUserSettingsResponse(userId, message, notificationType);
                            break;
                        case USER_VERIFIED:
                            success = processUserVerifiedResponse(userId, message, notificationType);
                            break;
                        case USER_DELETE:
                            success = processUserDeletedResponse(userId, message, notificationType);
                            break;
                        case EVENT_RSVP:
                            success = processEventRSVPResponse(eventId, userId, message, notificationType);
                            clearMessageHistory(userId);
                            break;
                        case QUESTION_ASKED:
                            success = processQuestionAskedResponse(
                                    questionId,
                                    userId,
                                    message,
                                    messageEntity.getTime(),
                                    notificationType);
                            clearMessageHistory(userId);
                            break;
                        default:
                            break;
                    }
                    if (!success) {
                        notificationService.resend(
                                userId,
                                notificationType,
                                messageEntity.getNotificationEventType(),
                                message,
                                messageEntity.getMessage());
                    }
                } else {
                    if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
                        handleStop(userId, notificationType);
                    }
                }
            } catch (ResourceNotFoundException e) {
                LOGGER.warn(String.format("No user found for user [%s]", to));
            }
        }
    }

    /**
     * Handles STOP
     *
     * @param userId user ID
     * @param notificationType NotificationType
     * @throws ResourceNotFoundException when no user is found
     */
    protected void handleStop(Long userId, NotificationType notificationType) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        switch (notificationType) {
            case SLACK:
                user.setSlackEnabled(false);
                break;
            case SMS:
                user.setSmsEnabled(false);
                break;
            default:
        }
        userService.store(user);
    }

    /**
     * Process user verified response
     *
     * @param userId user ID
     * @param message to be processed
     * @param notificationType NotificationType
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    protected boolean processUserVerifiedResponse(Long userId, String message, NotificationType notificationType)
            throws ResourceNotFoundException {
        if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
            handleStop(userId, notificationType);
        }
        return true;
    }

    /**
     * Process event RSVP response
     *
     * @param eventId event ID
     * @param userId user ID
     * @param message to be processed
     * @param notificationType NotificationType
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    protected boolean processEventRSVPResponse(
            Long eventId,
            Long userId,
            String message,
            NotificationType notificationType) throws ResourceNotFoundException {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            switch (ResponseParser.determineResponse(message)) {
                case STOP:
                    handleStop(userId, notificationType);
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
     * Asks the next question in a quiz, if applicable
     *
     * @param userId User ID
     * @param eventId Event ID
     * @param quizId Quiz ID
     */
    protected void askNextQuestion(Long userId, Long eventId, Long quizId) {
        if (userId == null || eventId == null || quizId == null) {
            return;
        }
        final Long nextQuestionId = quizService.getNextQuestion(quizId, userId);
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
        } else {
            try {
                notificationService.send(
                        userId,
                        eventId,
                        null,
                        NotificationType.ALL,
                        NotificationEventType.QUIZ_COMPLETE);
            } catch (ResourceNotFoundException e) {
                LOGGER.warn(String.format("Exception %s", e.getMessage()));
            }
        }
    }

    /**
     * Process user deleted response
     *
     * @param userId user ID
     * @param message to be processed
     * @param notificationType NotificationType
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    protected boolean processUserDeletedResponse(Long userId, String message, NotificationType notificationType)
            throws ResourceNotFoundException {
        if (message != null && ResponseOption.STOP == ResponseParser.determineResponse(message)) {
            handleStop(userId, notificationType);
        }
        return true;
    }

    /**
     * Process user settings response
     *
     * @param userId user ID
     * @param message to be processed
     * @param notificationType NotificationType
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    protected boolean processUserSettingsResponse(Long userId, String message, NotificationType notificationType)
            throws ResourceNotFoundException {
        boolean success = true;
        User user = null;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            switch (ResponseParser.determineResponse(message)) {
                case STOP:
                    handleStop(userId, notificationType);
                    break;
                case CONFIRM:
                    user = userService.findById(userId);
                    if (notificationType == NotificationType.SLACK) {
                        user.setSlackVerified(true);
                    } else if (notificationType == NotificationType.SMS) {
                        user.setSmsVerified(true);
                    }
                    userService.store(user);
                    notificationService.send(
                            userId,
                            null,
                            null,
                            notificationType,
                            NotificationEventType.USER_VERIFIED);
                    break;
                case DECLINE:
                    user = userService.findById(userId);
                    if (notificationType == NotificationType.SLACK) {
                        user.setSlackEnabled(false);
                    } else if (notificationType == NotificationType.SMS) {
                        user.setSmsEnabled(false);
                    }
                    userService.store(user);
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
     * @param startTime when question was asked
     * @param notificationType NotificationType
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    protected boolean processQuestionAskedResponse(
            Long questionId,
            Long userId,
            String message,
            Date startTime,
            NotificationType notificationType) throws ResourceNotFoundException {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        if (message != null) {
            ResponseOption responseOption = ResponseParser.determineResponse(message);
            switch (responseOption) {
                case STOP:
                    handleStop(userId, notificationType);
                    break;
                case SKIP:
                case A:
                case B:
                case C:
                case D:
                    if (responseOption != ResponseOption.SKIP) {
                        questionService.answer(questionId, userId, responseOption.toString(), startTime);
                    }
                    askNextQuestion(userId, eventService.getCurrentEvent(), quizService.getCurrentQuiz());
                    break;
                default:
                    success = false;
            }
        }
        return success;
    }

    /**
     * Retrieves the last message sent to an entity
     *
     * @param to message recipient
     * @return SlackMessageEntity
     */
    protected MessageEntity getLastMessage(String to) {
        MessageEntity messageEntity = null;
        List<MessageEntity> messages = messageRepository.findByTo(to);
        if (messages != null && messages.size() > 0) {
            TreeMap<Date, MessageEntity> map = new TreeMap<>();
            for (MessageEntity message : messages) {
                map.put(message.getTime(), message);
            }
            Map.Entry<Date, MessageEntity> lastEntry = map.lastEntry();
            messageEntity = lastEntry.getValue();
        }
        return messageEntity;
    }

    /**
     * Strips the country code from a phone number
     *
     * @param from number
     * @return phone number minus country code
     */
    protected static String stripCountryCode(String from) {
        if (from == null || from.length() == 10) {
            return from;
        }
        String response = from;
        Matcher matcher = TN_PATTERN.matcher(from);
        if (matcher.find()) {
            response = matcher.group(1);
        }
        return response;
    }

}
