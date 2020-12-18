package com.telesoftas.adventofcode.operationorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

public class OperationOrder {

    static long evaluateExpression(String expression) {
        final Pattern pattern = Pattern.compile("\\(([0-9+* ]+)\\)");

        while (expression.indexOf('(') != -1) {
            final Matcher matcher = pattern.matcher(expression);
            while (matcher.find()) {
                expression = matcher.replaceAll(matchResult -> valueOf(evaluateExpression(matchResult.group(1))));
            }
        }

        final String[] members = expression.split(" ");
        return evaluateExpression(members);
    }

    static long evaluateExpression(String[] members) {
        long value = 0;
        String op = "+";
        for (int i = 0; i < members.length; i++) {
            String member = members[i];
            if ("+".equals(member) || "*".equals(member)) {
                op = member;
                continue;
            }
            value = operation(value, op, parseLong(member));
        }
        return value;
    }

    private static long operation(long value, String op, long member) {
        return switch (op) {
            case "+" -> value + member;
            case "*" -> value * member;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }
}
