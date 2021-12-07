package com.telesoftas.adventofcode.operationorder;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@UtilityClass
public class OperationOrder {

    public static final Pattern PARENTHESES = Pattern.compile("\\(([0-9+* ]+)\\)");
    public static final Pattern ADDITION = Pattern.compile("(\\d+ \\+ \\d+)");

    static long evaluateExpression(String expression) {
        while (expression.indexOf('(') != -1) {
            final Matcher matcher = PARENTHESES.matcher(expression);
            while (matcher.find()) {
                expression = matcher.replaceAll(matchResult -> valueOf(evaluateExpression(matchResult.group(1))));
            }
        }

        return evaluateExpression(expression.split(" "));
    }

    public static long evaluateExpressionWithAdditionPrecedence(String expression) {
        while (expression.indexOf('(') != -1) {
            final Matcher matcher = PARENTHESES.matcher(expression);
            while (matcher.find()) {
                expression = matcher.replaceAll(m -> valueOf(evaluateExpressionWithAdditionPrecedence(m.group(1))));
            }
        }

        while (expression.indexOf('+') != -1) {
            final Matcher matcher = ADDITION.matcher(expression);
            while (matcher.find()) {
                expression = matcher.replaceAll(matchResult -> {
                    final String[] parts = matchResult.group(1).split(" ");
                    return valueOf(operation(parseLong(parts[0]), parts[1], parseLong(parts[2])));
                });
            }
        }

        return evaluateExpression(expression.split(" "));
    }

    static long evaluateExpression(String[] members) {
        long value = 0;
        String op = "+";
        for (String member : members) {
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
