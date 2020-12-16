package com.telesoftas.adventofcode.tickettranslation;

import java.util.Arrays;
import java.util.List;

public record RuleSet(List<Rule> rules) {

    public boolean complies(int value) {
        return rules.stream()
            .flatMap(rule -> Arrays.stream(rule.ranges()))
            .anyMatch(range -> range.inRangeInclusive(value));
    }
}
