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

import static java.lang.Integer.parseInt;

@Log4j2
public class Day7 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day7.txt")) {
            Pattern pattern = Pattern.compile("(?<amount>\\d+) (?<color>.*?) bags?");
            var rulesByColor = toStringStream(input)
                .map(s -> {
                    String[] parts = s.split(" bags contain ");
                    var allowed = parts[1].split(", ");

                    Set<Bag> colors = Arrays.stream(allowed)
                        .map(pattern::matcher)
                        .filter(Matcher::matches)
                        .map(matcher -> new Bag(matcher.group("color"), parseInt(matcher.group("amount"))))
                        .collect(Collectors.toSet());

                    return new BagRule(new Bag(parts[0]), colors);
                })
                .collect(Collectors.toMap(BagRule::getBag, Function.identity()));

            // How many bag colors can eventually contain at least one shiny gold bag?
            var answer = rulesByColor.values().stream()
                .filter(bagRule -> canContainDeep(rulesByColor, bagRule))
                .count();

            log.info("Answer: {}", answer);

            // How many individual bags are required inside your single shiny gold bag?
            var answer2 = countInnerBagsDeep(rulesByColor, rulesByColor.get(Bag.SHINY_GOLD));

            log.info("Answer: {}", answer2);
        }
    }

    private static int countInnerBagsDeep(Map<Bag, BagRule> rulesByColor, BagRule bagRule) {
        int sum = 0;
        for (Bag innerBag : bagRule.getInnerBags()) {
            sum += innerBag.getAmount();
            sum += innerBag.getAmount() * countInnerBagsDeep(rulesByColor, rulesByColor.get(innerBag));
        }
        return sum;
    }

    private static boolean canContainDeep(Map<Bag, BagRule> bagsByColor, BagRule bagRule) {
        if (bagRule.canContain(Bag.SHINY_GOLD)) {
            return true;
        }

        for (Bag innerBagColor : bagRule.getInnerBags()) {
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
