package com.telesoftas.adventofcode.tickettranslation;

import java.util.List;

public record Ticket(List<Integer> values) {

    public int value(int index) {
        return values.get(index);
    }

    long departureFieldsProductByMap(Rule[] fieldToRule) {
        long multi = 1;
        for (int i = 0, length = fieldToRule.length; i < length; i++) {
            Rule rule = fieldToRule[i];
            if (rule.name().startsWith("departure")) {
                multi *= value(i);
            }
        }

        return multi;
    }
}
