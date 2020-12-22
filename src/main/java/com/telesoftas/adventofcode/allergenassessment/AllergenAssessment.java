package com.telesoftas.adventofcode.allergenassessment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;

public record AllergenAssessment(List<Food> foods) {

    public long ingredientsWithoutAllergensAmount() {
        final Map<String, String> ingredientsWithAllergen = ingredientsWithAllergen();

        return foods.stream()
            .mapToLong(food -> food.ingredients().stream().filter(not(ingredientsWithAllergen::containsKey)).count())
            .sum();
    }

    private Map<String, String> ingredientsWithAllergen() {
        Map<String, Set<String>> map = new HashMap<>();

        foods.forEach(food -> {
            food.allergens().forEach(allergen -> {
                map.compute(allergen, (key, value) -> {
                    if (value == null) {
                        return new HashSet<>(food.ingredients());
                    }
                    value.retainAll(food.ingredients());
                    return value;
                });
            });
        });

        final Map<String, String> ingredient2allergen = new HashMap<>();

        while (!map.isEmpty()) {
            map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == 1)
                .findFirst()
                .ifPresent(entry -> {
                    final String ingredient = entry.getValue().iterator().next();
                    ingredient2allergen.put(ingredient, entry.getKey());
                    map.remove(entry.getKey());
                    map.forEach((key, ingredients) -> ingredients.remove(ingredient));
                });
        }
        return ingredient2allergen;
    }

    public String canonicalDangerousIngredientList() {
        return ingredientsWithAllergen().entrySet().stream()
            .sorted(Entry.comparingByValue())
            .map(Entry::getKey)
            .collect(joining(","));
    }
}
