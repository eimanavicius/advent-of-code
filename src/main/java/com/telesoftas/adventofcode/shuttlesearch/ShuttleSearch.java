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
            .filter(integer -> integer > 1)
            .collect(toMap(Function.identity(), buss -> ((departure / buss + 1) * buss) - departure));

        final Optional<Entry<Integer, Integer>> bussWaitMinutes = map
            .entrySet()
            .stream()
            .min(Entry.comparingByValue());

        return bussWaitMinutes.map(buss -> buss.getKey() * buss.getValue()).orElse(-1);
    }

    public long nextTimeMatchingOffset() {
        long timestamp = 0;
        long multiple = 1;
        for (int offset = 0; offset < busses.size(); offset++) {
            long buss = busses.get(offset);
            while (0 != (timestamp + offset) % buss) {
                timestamp += multiple;
            }
            multiple *= buss;
        }

        return timestamp;
    }
}
