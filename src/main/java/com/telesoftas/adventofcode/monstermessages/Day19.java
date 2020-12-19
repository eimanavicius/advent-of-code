package com.telesoftas.adventofcode.monstermessages;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day19 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day19.txt")) {
            final MonsterMessages messages = toMonsterMessages(requireNonNull(input));

            log.info("Answer: {}", messages.countValidMessages());
        }
    }

    public static MonsterMessages toMonsterMessages(InputStream input) {
        final Scanner scanner = new Scanner(input);

        RuleSet rules = new RuleSet();
        for (String rule = scanner.nextLine(); !rule.isBlank(); rule = scanner.nextLine()) {
            final String[] parts = rule.split(": | \\| ");
            if (parts[1].charAt(0) == '"') {
                rules.putSymbolMatch(parseInt(parts[0]), parts[1].charAt(1));
                continue;
            }
            Set<int[]> seq = new HashSet<>();
            for (int i = 1; i < parts.length; i++) {
                seq.add(
                    stream(parts[i].split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray()
                );
            }
            rules.putSequenceMatch(parseInt(parts[0]), seq);
        }

        List<String> messages = scanner.useDelimiter("\\n")
            .tokens()
            .collect(toList());

        return new MonsterMessages(rules, messages);
    }
}
