package com.telesoftas.adventofcode.monstermessages;

import lombok.Value;

import java.util.HashMap;
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
                .findAny()
                .orElse(0);
        }
    }
}
