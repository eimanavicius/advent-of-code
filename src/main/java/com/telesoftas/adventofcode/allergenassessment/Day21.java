package com.telesoftas.adventofcode.allergenassessment;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day21 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day21.txt")) {
            final AllergenAssessment allergens = toAllergenAssessment(requireNonNull(input));

            log.info("Answer: {}", allergens.ingredientsWithoutAllergensAmount());
            log.info("Answer: {}", allergens.canonicalDangerousIngredientList());
        }
    }

    public static AllergenAssessment toAllergenAssessment(InputStream input) {
        return new Scanner(input)
            .useDelimiter("\n")
            .tokens()
            .map(food -> food.split(" \\(contains |\\)"))
            .map(food -> new Food(Set.of(food[0].split(" ")), Set.of(food[1].split(", "))))
            .collect(collectingAndThen(toList(), AllergenAssessment::new));
    }
}
