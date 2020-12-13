package com.telesoftas.adventofcode.shuttlesearch;

import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Value
public class ShuttleSearch {

    int departure;

    List<Integer> busses;

    public int busMultipliedByWaitMinutes() {
        final Map<Integer, Integer> map = busses.stream()
            .collect(toMap(Function.identity(), buss -> ((departure / buss + 1) * buss) - departure));

        final Optional<Entry<Integer, Integer>> bussWaitMinutes = map
            .entrySet()
            .stream()
            .min(Entry.comparingByValue());

        // 59 * 5
        return bussWaitMinutes.map(buss -> buss.getKey() * buss.getValue()).orElse(-1);
    }
}
