package com.telesoftas.adventofcode.tickettranslation;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day16 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day16.txt")) {
            final TicketTranslation tickets = toTicketTranslation(requireNonNull(input));

            log.info("Answer: {}", tickets.ticketScanningErrorRate());
            log.info("Answer: {}", tickets.departureFieldsProduct());
        }
    }

    public static TicketTranslation toTicketTranslation(InputStream input) {
        final Scanner scanner = new Scanner(input);

        List<Rule> rules = new ArrayList<>();
        for (String rule = scanner.nextLine(); !rule.isBlank(); rule = scanner.nextLine()) {
            final String[] parts = rule.split(": | or ");
            rules.add(new Rule(parts[0], new Range[]{Range.valueOf(parts[1]), Range.valueOf(parts[2])}));
        }

        // your ticket:
        scanner.nextLine();
        Ticket myTicket = null;
        for (String ticketLine = scanner.nextLine(); !ticketLine.isBlank(); ticketLine = scanner.nextLine()) {
            myTicket = new Ticket(stream(ticketLine.split(",")).map(Integer::valueOf).collect(toList()));
        }

        // nearby tickets:
        scanner.nextLine();
        List<Ticket> tickets = scanner.useDelimiter("\\n")
            .tokens()
            .map(ticketLine -> new Ticket(stream(ticketLine.split(",")).map(Integer::valueOf).collect(toList())))
            .collect(toList());

        return new TicketTranslation(new RuleSet(rules), tickets, myTicket);
    }
}
