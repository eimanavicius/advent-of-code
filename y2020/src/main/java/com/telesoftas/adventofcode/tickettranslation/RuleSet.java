package com.telesoftas.adventofcode.tickettranslation;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public record RuleSet(List<Rule> rules) {

    public boolean complies(int value) {
        return rules.stream()
            .flatMap(rule -> Arrays.stream(rule.ranges()))
            .anyMatch(range -> range.inRangeInclusive(value));
    }

    public Set<Rule> findMatchingRules(Integer value) {
        return rules.stream()
            .filter(rule -> rule.matches(value))
            .collect(toSet());
    }
}
