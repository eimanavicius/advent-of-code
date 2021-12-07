package com.telesoftas.adventofcode.monstermessages;

import lombok.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class RuleSet {

    private final Map<Integer, Rule> rules = new HashMap<>();

    public void putSymbolMatch(int key, char symbol) {
        rules.put(key, new SymbolRule(symbol));
    }

    public void putSequenceMatch(int key, Set<int[]> sequence) {
        rules.put(key, new RefRule(sequence));
    }

    public boolean isMessageValid(String message) {
        return message.length() == rules.get(0).isValid(message, 0);
    }

    public boolean isMessageValidSpecialized0Loops(String message) {
        final Rule rule8 = rules.get(8);
        final Rule rule11 = sequenceMatch11(10);

        final int length = message.length();

        for (int i = 0; i < 10; i++) {
            int start = 0;
            for (int j = 0; j <= i; j++) {
                final int valid = rule8.isValid(message, start);
                if (valid == 0 || start + valid >= length) {
                    return false;
                }
                start += valid;
            }
            if (length == start + rule11.isValid(message, start)) {
                return true;
            }
        }
        return false;
    }

    private Rule sequenceMatch11(int times) {
        final HashSet<int[]> sequence = new LinkedHashSet<>();
        final int amount = 2 * times;
        for (int i = 2; i <= amount; i += 2) {
            final int[] ints = new int[i];
            final int half = ints.length / 2;
            for (int i1 = 0; i1 < ints.length; i1++) {
                ints[i1] = i1 < half ? 42 : 31;
            }
            sequence.add(ints);
        }
        return new RefRule(sequence);
    }

    public interface Rule {

        int isValid(String message, int start);
    }

    private static record SymbolRule(char symbol) implements Rule {

        @Override
        public int isValid(String message, int start) {
            if (start >= message.length()) {
                return 0;
            }
            return message.charAt(start) == symbol ? 1 : 0;
        }
    }

    @Value
    private class RefRule implements Rule {

        Set<int[]> sequence;

        @Override
        public int isValid(String message, int start) {
            return sequence.stream()
                .map(refRules -> {
                    int sum = 0;
                    int ruleStart = start;
                    for (int refRule : refRules) {
                        if (ruleStart >= message.length()) {
                            return 0;
                        }
                        final Rule rule = rules.get(refRule);
                        final int matched = rule.isValid(message, ruleStart);
                        if (matched == 0) {
                            return 0;
                        }
                        ruleStart += matched;
                        sum += matched;
                    }
                    return sum;
                })
                .filter(matched -> matched > 0)
                .findFirst()
                .orElse(0);
        }
    }
}
