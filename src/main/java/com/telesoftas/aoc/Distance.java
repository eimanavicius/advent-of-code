package com.telesoftas.aoc;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Distance {

    public static void main(String[] args) {
        Distance distance = new Distance();

        String input = "R8,U5,L5,D3\n" +
                       "U7,R6,D4,L4";

        String[] split = input.split("\n");

        var wire1 = distance.calculateCoords(split[0]);
        var wire2 = distance.calculateCoords(split[1]);

        wire1.entrySet().stream()
            .filter(entry -> wire2.containsKey(entry.getKey()))
            .map(entry -> wire2.get(entry.getKey()) + entry.getValue())
            .min(Integer::compareTo)
            .ifPresent(System.out::println);
    }

    public Map<Point, Integer> calculateCoords(String vectors) {
        int x = 0;
        int y = 0;
        AtomicInteger points = new AtomicInteger();

        Map<Point, Integer> wire = new HashMap<>();
        // one wire here
        for (String vector : vectors.split(",")) {
            int distance = Integer.parseInt(vector.substring(1));
            switch (vector.charAt(0)) {
                case 'R':
                    for (int i = 0; i < distance; i++) {
                        wire.computeIfAbsent(new Point(++x, y), __ -> points.incrementAndGet());
                    }
                    break;
                case 'U':
                    for (int i = 0; i < distance; i++) {
                        wire.computeIfAbsent(new Point(x, ++y), __ -> points.incrementAndGet());
                    }
                    break;
                case 'L':
                    for (int i = 0; i < distance; i++) {
                        wire.computeIfAbsent(new Point(--x, y), __ -> points.incrementAndGet());
                    }
                    break;
                case 'D':
                    for (int i = 0; i < distance; i++) {
                        wire.computeIfAbsent(new Point(x, --y), __ -> points.incrementAndGet());
                    }
                    break;
            }
        }

        return wire;
    }
}
