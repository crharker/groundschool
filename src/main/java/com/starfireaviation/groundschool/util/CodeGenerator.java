/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.util;

import java.util.Random;

/**
 * CodeGenerator
 *
 * @author brianmichael
 */
public class CodeGenerator {

    /**
     * ACCEPTABLE_CHARACTERS
     */
    public static final String[] ACCEPTABLE_CHARACTERS = {
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "J",
            "K",
            "M",
            "N",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "X",
            "Y",
            "Z"
    };

    /**
     * Generates a random code of the length specified
     *
     * @param length of the resultant code
     * @return generated code
     */
    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ACCEPTABLE_CHARACTERS[getRandomNumberInRange(0, ACCEPTABLE_CHARACTERS.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates a random integer between min (inclusive) and max (inclusive).
     *
     * @param min minimum
     * @param max maximum
     * @return random number within range
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
