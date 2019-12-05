package com.telesoftas.aoc;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Distance {

    public static void main(String[] args) {
        Distance distance = new Distance();

        String input = "R8,U5,L5,D3\n" +
            "U7,R6,D4,L4";

        String[] split = input.split("\n");

        Set<Point> wire1 = distance.calculateCoords(split[0]);
        Set<Point> wire2 = distance.calculateCoords(split[1]);

        wire1.retainAll(wire2);

        wire1.stream()
            .map(point -> Math.abs(point.x) + Math.abs(point.y))
            .min(Integer::compareTo)
            .ifPresent(System.out::println);
    }

    public Set<Point> calculateCoords(String vectors) {
        int x = 0;
        int y = 0;

        Set<Point> wire = new HashSet<>();
        // one wire here
        for (String vector : vectors.split(",")) {
            int distance = Integer.parseInt(vector.substring(1));
            switch (vector.charAt(0)) {
                case 'R':
                    for (int i = 0; i < distance; i++) {
                        wire.add(new Point(++x, y));
                    }
                    break;
                case 'U':
                    for (int i = 0; i < distance; i++) {
                        wire.add(new Point(x, ++y));
                    }
                    break;
                case 'L':
                    for (int i = 0; i < distance; i++) {
                        wire.add(new Point(--x, y));
                    }
                    break;
                case 'D':
                    for (int i = 0; i < distance; i++) {
                        wire.add(new Point(x, --y));
                    }
                    break;
            }
        }

        return wire;
    }
}
