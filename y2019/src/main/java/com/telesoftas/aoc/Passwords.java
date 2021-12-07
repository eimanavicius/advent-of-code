package com.telesoftas.aoc;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.stream.IntStream;

public class Passwords {

    public static void main(String[] args) {
    }

    public long count() {
        return IntStream.rangeClosed(108457, 562041)
            .filter(this::hasDoubleDigits)
            .filter(this::isIncreasingOrEqual)
            .filter(this::validGroups)
//            .filter(value -> {
//                return this.isIncreasingOrEqual(value) && String.valueOf(value).chars().distinct().count() < 6;
//            })
            .count();
    }

    public boolean validGroups(final int password) {
        return String.valueOf(password)
            .chars()
            .boxed()
            .collect(groupingBy(identity(), counting()))
            .containsValue(2L);
    }

    public boolean hasDoubleDigits(final int password) {
        String string = String.valueOf(password);
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIncreasingOrEqual(final int password) {
        String string = String.valueOf(password);
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) < string.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
