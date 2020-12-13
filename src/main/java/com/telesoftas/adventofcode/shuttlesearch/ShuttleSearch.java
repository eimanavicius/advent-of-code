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
            .filter(integer -> integer > 0)
            .collect(toMap(Function.identity(), buss -> ((departure / buss + 1) * buss) - departure));

        final Optional<Entry<Integer, Integer>> bussWaitMinutes = map
            .entrySet()
            .stream()
            .min(Entry.comparingByValue());

        // 59 * 5
        return bussWaitMinutes.map(buss -> buss.getKey() * buss.getValue()).orElse(-1);
    }

    public long nextTimeMatchingOffset() {
        long shared = 1;
        for (Integer buss : busses) {
            if (buss > 0) {
                shared *= buss;
            }
        }

        final Integer inc = busses.get(0);
        for (long same = shared-inc; same > 0; same -= inc) {
            boolean divides = true;
            for (long i = 0; i < busses.size(); i++) {
                final long buss = busses.get((int) i);
                if (buss == 0) {
                    continue;
                }
                divides = ((same + i) % buss) == 0;
                if (!divides) {
                    break;
                }
            }
            if (divides) {
                return same;
            }
        }

        return shared;
    }
}
