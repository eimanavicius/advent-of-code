package com.telesoftas.adventofcode.day07;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TheTreacheryOfWhales {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day07.txt")) {
            int[] positions = toNumbersArray(input);

            System.out.println("Part 1:");
            System.out.println(findFuelRequiredToAlignPositions(positions));
            System.out.println("Part 2:");
            System.out.println(findFuelRequiredToAlignPositionsWithProgressiveCost(positions));
        }
    }

    public static int findFuelRequiredToAlignPositions(int... positions) {
        return possiblePositions(positions)
            .map(positionToAlign -> {
                return Arrays.stream(positions)
                    .map(position -> Math.abs(position - positionToAlign))
                    .sum();
            })
            .min().orElseThrow();
    }


    public static int findFuelRequiredToAlignPositionsWithProgressiveCost(int... positions) {
        return possiblePositions(positions)
            .map(positionToAlign -> {
                return Arrays.stream(positions)
                    .map(position -> progressiveFuelCost(positionToAlign, position))
                    .sum();
            })
            .min().orElseThrow();
    }

    private static IntStream possiblePositions(int[] positions) {
        int min = Arrays.stream(positions).min().orElseThrow();
        int max = Arrays.stream(positions).max().orElseThrow();
        return IntStream.range(min, max + 1);
    }

    public static int progressiveFuelCost(int positionToAlign, int position) {
        if (position == positionToAlign) {
            return 0;
        }
        return IntStream.range(1, Math.abs(position - positionToAlign) + 1).sum();
    }

    static int[] toNumbersArray(InputStream input) {
        return new Scanner(input)
            .useDelimiter(",")
            .tokens()
            .mapToInt(Integer::valueOf)
            .toArray();
    }
}
