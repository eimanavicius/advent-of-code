package com.telesoftas.adventofcode.crabcups;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Log4j2
public class CrabCups {

    private final List<Integer> cups;

    public CrabCups(List<Integer> cups) {
        this.cups = new ArrayList<>(cups);
    }

    public String cupsAfter1AsString(int moves) {
        for (int move = 0; move < moves; move++) {
            log.trace("-- move {} --", move + 1);
            log.trace(this::cupsToString);
            Integer current = cups.remove(0);
            List<Integer> pickUp = List.of(cups.remove(0), cups.remove(0), cups.remove(0));
            log.trace(() -> "pick up: " + String.join(
                ", ",
                pickUp.stream().map(String::valueOf).collect(joining(", "))
            ));
            Integer destination = current - 1;
            while (!cups.contains(destination)) {
                destination--;
                if (destination < 1) {
                    destination = cups.stream().max(Integer::compareTo).orElseThrow();
                }
            }
            log.trace("destination: {}", destination);
            for (int i = 0; i < pickUp.size(); i++) {
                cups.add(cups.indexOf(destination) + 1 + i, pickUp.get(i));
            }
            cups.add(current);

            log.trace("");
        }

        log.trace("-- final --");
        log.trace(this::cupsToString);
        log.trace("");

        final int size = cups.size();
        int afterOne = cups.indexOf(1);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < cups.size() - 1; i++) {
            afterOne = (afterOne + 1) % size;
            line.append(cups.get(afterOne));
        }
        return line.toString();
    }

    private String cupsToString() {
        StringBuilder builder = new StringBuilder("cups: (");
        builder.append(cups.get(0));
        builder.append(")");
        for (int i = 1; i < cups.size(); i++) {
            builder.append(" ");
            builder.append(cups.get(i));
        }
        return builder.toString();
    }
}
