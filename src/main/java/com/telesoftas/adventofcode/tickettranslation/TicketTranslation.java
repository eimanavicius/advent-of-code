package com.telesoftas.adventofcode.tickettranslation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.copyOf;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public record TicketTranslation(RuleSet rules, List<Ticket> tickets, Ticket myTicket) {

    public int ticketScanningErrorRate() {
        return tickets.stream()
            .mapToInt(
                ticket -> ticket.values()
                    .stream()
                    .filter(not(rules::complies))
                    .mapToInt(value -> value)
                    .sum()
            )
            .sum();
    }

    public long departureFieldsProduct() {
        final List<Ticket> validTickets = tickets.stream()
            .filter(ticket -> ticket.values().stream().noneMatch(not(rules::complies)))
            .collect(toList());

        Map<Integer, Map<Rule, Integer>> fields = new HashMap<>();
        inspectTicket(myTicket, fields);
        validTickets.forEach(ticket -> inspectTicket(ticket, fields));

        final Map<Integer, Set<Rule>> fieldsSimple = mapToPossibleFields(fields, validTickets.size() + 1);

        return findFieldMapping(fieldsSimple)
            .map(myTicket::departureFieldsProductByMap)
            .orElseThrow();
    }

    private void inspectTicket(Ticket ticket, Map<Integer, Map<Rule, Integer>> fields) {
        final List<Integer> values = ticket.values();
        for (int fieldIndex = 0, size = values.size(); fieldIndex < size; fieldIndex++) {
            final Integer value = values.get(fieldIndex);
            Map<Rule, Integer> matchingRulesForField = rules.findMatchingRules(value)
                .stream()
                .collect(toMap(identity(), rule -> 1));
            fields.merge(fieldIndex, new HashMap<>(matchingRulesForField), (oldValue, newValue) -> {
                newValue.forEach((key, count) -> oldValue.merge(key, count, Integer::sum));
                return oldValue;
            });
        }
    }


    private Map<Integer, Set<Rule>> mapToPossibleFields(Map<Integer, Map<Rule, Integer>> fields, int totalTicketCount) {
        return fields.entrySet().stream()
            .map(entry -> Map.entry(
                entry.getKey(),
                entry.getValue()
                    .entrySet()
                    .stream()
                    .filter(r -> r.getValue() == totalTicketCount)
                    .map(Entry::getKey)
                    .collect(toSet())
            ))
            .collect(toMap(Entry::getKey, Entry::getValue));
    }

    private Optional<Rule[]> findFieldMapping(Map<Integer, Set<Rule>> fields) {
        final int fieldCount = fields.keySet().size();
        final Rule[] fieldToRule = new Rule[fieldCount];
        return ofNullable(findNextField(fieldToRule, 0, fields));
    }

    private Rule[] findNextField(Rule[] fieldToRule, int nextField, Map<Integer, Set<Rule>> fields) {
        if (nextField >= fieldToRule.length) {
            return fieldToRule;
        }
        var matchingRules = fields.get(nextField);
        for (Rule rule : matchingRules) {
            if (alreadyContains(fieldToRule, rule)) {
                continue;
            }
            final Rule[] rulesCopy = copyOf(fieldToRule, fieldToRule.length);
            rulesCopy[nextField] = rule;
            final Rule[] possibility = findNextField(rulesCopy, nextField + 1, fields);
            if (possibility != null) {
                return possibility;
            }
        }
        return null;
    }

    private boolean alreadyContains(Rule[] fieldToRule, Rule rule) {
        for (Rule r : fieldToRule) {
            if (r == null) {
                return false;
            }
            if (r.equals(rule)) {
                return true;
            }
        }

        return false;
    }
}
