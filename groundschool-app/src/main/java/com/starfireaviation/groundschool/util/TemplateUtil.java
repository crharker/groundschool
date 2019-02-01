/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.User;

/**
 * TemplateUtil
 *
 * @author brianmichael
 */
public class TemplateUtil {

    /**
     * SimpleDateFormat
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("EEE dd/MM/yyyy hh:mm a");

    /**
     * Builds model for use in templates
     *
     * @param user User
     * @param event Event
     * @param question Question
     * @return model
     */
    public static Map<String, Object> getModel(final User user, final Event event, final Question question) {
        Map<String, Object> model = new HashMap<>();
        // TODO property file this value
        model.put("groundSchoolLink", "http://joomla.eaa690.net/index.php/programs/ground-school");
        model.put("groundSchoolPasswordResetLink", "");
        buildUserModel(user, model);
        buildEventModel(event, model);
        buildQuestionModel(question, model);
        return model;
    }

    /**
     * Builds Question portions of model for use in templates
     *
     * @param question Question
     * @param model Map
     */
    private static void buildQuestionModel(final Question question, Map<String, Object> model) {
        if (question != null) {
            model.put("questionUnit", question.getUnit());
            model.put("questionSubUnit", question.getSubUnit());
            model.put("questionLearningStatementCode", question.getLearningStatementCode());
            model.put("questionText", question.getText());
            model.put("callbackId", "question");
            final List<ReferenceMaterial> referenceMaterials = question.getReferenceMaterials();
            if (referenceMaterials != null && referenceMaterials.size() > 0) {
                StringBuilder sb = new StringBuilder("Reference Material: ");
                for (ReferenceMaterial referenceMaterial : referenceMaterials) {
                    sb.append("<" + referenceMaterial.getResourceLocation() + ">\n");
                }
                model.put("referenceMaterial", sb.toString());
            }
            int count = 1;
            if (question.getAnswers() != null) {
                for (Answer answer : question.getAnswers()) {
                    model.put("answerChoice" + count, answer.getChoice());
                    model.put("answerText" + count, answer.getText());
                    count++;
                }
            }
        }
    }

    /**
     * Builds Event portions of model for use in templates
     *
     * @param event Event
     * @param model Map
     */
    private static void buildEventModel(final Event event, Map<String, Object> model) {
        if (event != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(event.getTitle());
            sb.append("\n\n");
            sb.append("Time: ");
            sb.append(sdf.format(Date.from(event.getStartTime().toInstant(ZoneOffset.UTC))));
            sb.append("\n");
            Address address = event.getAddress();
            if (address != null) {
                sb.append("Address: \n");
                if (address.getAddressLine1() != null) {
                    sb.append("\t");
                    sb.append(address.getAddressLine1());
                    sb.append("\n");
                }
                if (address.getAddressLine2() != null) {
                    sb.append("\t");
                    sb.append(address.getAddressLine2());
                    sb.append("\n");
                }
                sb.append("\t");
                sb.append(address.getCity());
                sb.append(", ");
                sb.append(address.getState());
                sb.append(" ");
                sb.append(address.getZipCode());
            }
            model.put("event", sb.toString());
        } else {
            model.put("event", "");
        }
    }

    /**
     * Builds User portions of model for use in templates
     *
     * @param user User
     * @param model Map
     */
    private static void buildUserModel(final User user, Map<String, Object> model) {
        if (user != null) {
            model.put("firstName", user.getFirstName());
            model.put("lastName", user.getLastName());
            model.put("userId", user.getId());
            model.put("code", user.getCode());
        } else {
            model.put("firstName", "");
            model.put("lastName", "");
            model.put("userId", "");
            model.put("code", "");
        }
    }

}
