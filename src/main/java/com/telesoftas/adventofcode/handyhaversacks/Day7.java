package com.telesoftas.adventofcode.handyhaversacks;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class Day7 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day7.txt")) {
            Pattern pattern = Pattern.compile("\\d+ (?<color>.*?) bags?");
            var rulesByColor = toStringStream(input)
                .map(s -> {
                    String[] parts = s.split(" bags contain ");
                    var allowed = parts[1].split(", ");

                    Set<String> colors = Arrays.stream(allowed)
                        .map(pattern::matcher)
                        .filter(Matcher::matches)
                        .map(matcher -> matcher.group("color"))
                        .collect(Collectors.toSet());

                    return new BagRule(parts[0], colors);
                })
                .collect(Collectors.toMap(BagRule::getBagColor, Function.identity()));

            var answer = rulesByColor.values().stream()
                .filter(bagRule -> canContainDeep(rulesByColor, bagRule))
                .count();

            log.info("Answer: {}", answer);
        }
    }

    private static boolean canContainDeep(Map<String, BagRule> bagsByColor, BagRule bagRule) {
        if (bagRule.canContain("shiny gold")) {
            return true;
        }

        for (String innerBagColor : bagRule.getInnerBagsColors()) {
            BagRule innerBagRule = bagsByColor.get(innerBagColor);
            if (canContainDeep(bagsByColor, innerBagRule)) {
                return true;
            }
        }

        return false;
    }

    public static Stream<String> toStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter(".\n")
            .tokens();
    }
}
