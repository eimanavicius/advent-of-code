package com.telesoftas.aoc;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Orbits {

    public Map<String, String> parseToMap(String input) {
        return Arrays.stream(input.split("\n"))
                .collect(toMap(this::getKey, this::getValue));
    }

    <U, T> String getKey(String t) {
        return t.substring(t.indexOf(")") + 1);
    }

    <K, T> String getValue(String t) {
        return t.substring(0, t.indexOf(")"));
    }

    public int calculate(Map<String, String> orbits) {
        return orbits.keySet().stream()
                .mapToInt(name -> getParents(name, orbits, 0))
                .sum();
    }

    private int getParents(String name, Map<String, String> orbits, int i) {
        if (!orbits.containsKey(name)) {
            return i;
        } else {
            return getParents(orbits.get(name), orbits, ++i);
        }
    }
}
