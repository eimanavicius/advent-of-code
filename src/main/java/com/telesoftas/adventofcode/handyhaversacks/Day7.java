package com.telesoftas.adventofcode.handyhaversacks;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.iterate;

@Log4j2
public class Day7 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day7.txt")) {
            Pattern pattern = Pattern.compile("(?<amount>\\d+) (?<color>.*?) bags?");
            var rulesByColor = toStringStream(input)
                .map(s -> s.split(" bags contain "))
                .map(parts -> {
                    final Bag bag = new Bag(parts[0]);
                    final Set<Bag> innerBags = iterate(pattern.matcher(parts[1]), Matcher::find, identity())
                        .map(matcher -> new Bag(matcher.group("color"), parseInt(matcher.group("amount"))))
                        .collect(toSet());
                    return new BagRule(bag, innerBags);
                })
                .collect(toMap(BagRule::getBag, identity()));

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
