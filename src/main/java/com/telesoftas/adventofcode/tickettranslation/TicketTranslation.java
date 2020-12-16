package com.telesoftas.adventofcode.tickettranslation;

import java.util.List;

import static java.util.function.Predicate.not;

public record TicketTranslation(RuleSet rules, List<Ticket> tickets) {

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
}
