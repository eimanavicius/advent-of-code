package com.telesoftas.adventofcode.day07;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

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
        return findFuelRequiredToAlignPositions(TheTreacheryOfWhales::fuelCost, positions);
    }

    public static int findFuelRequiredToAlignPositionsWithProgressiveCost(int... positions) {
        return findFuelRequiredToAlignPositions(TheTreacheryOfWhales::progressiveFuelCost, positions);
    }

    public static int findFuelRequiredToAlignPositions(IntBinaryOperator fuelCostFormula, int... positions) {
        return possiblePositions(positions)
            .map(alignTo -> stream(positions).map(calculator(fuelCostFormula, alignTo)).sum())
            .min().orElseThrow();
    }

    private static IntUnaryOperator calculator(IntBinaryOperator fuelCost, int positionToAlign) {
        return position -> fuelCost.applyAsInt(positionToAlign, position);
    }

    private static int fuelCost(int positionToAlign, int position) {
        return Math.abs(position - positionToAlign);
    }

    private static IntStream possiblePositions(int[] positions) {
        int min = stream(positions).min().orElseThrow();
        int max = stream(positions).max().orElseThrow();
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
