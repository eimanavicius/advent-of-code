package com.telesoftas.adventofcode.passportprocessing;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@UtilityClass
public class Height {
    private static final Pattern pattern = Pattern.compile("(\\d+)(cm|in)");

    public static Optional<Integer> parseCentimeters(String value) {
        if (value == null) {
            return empty();
        }

        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            return empty();
        }

        float multiplier = 1;
        if ("in".equals(matcher.group(2))) {
            multiplier = 2.54f;
        }

        return of(Math.round(Float.parseFloat(matcher.group(1)) * multiplier));
    }
}
