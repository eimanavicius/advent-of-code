package com.telesoftas.adventofcode.day07;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class TheTreacheryOfWhales {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day07.txt")) {
            int[] positions = toNumbersArray(input);

            System.out.println(findFuelRequiredToAlignPositions(positions));
        }
    }

    public static int findFuelRequiredToAlignPositions(int... positions) {
        return Arrays.stream(positions)
            .distinct()
            .map(positionToAlign -> {
                return Arrays.stream(positions)
                    .map(position -> Math.abs(position - positionToAlign))
                    .sum();
            })
            .min().orElseThrow();
    }


    static int[] toNumbersArray(InputStream input) {
        return new Scanner(input)
            .useDelimiter(",")
            .tokens()
            .mapToInt(Integer::valueOf)
            .toArray();
    }
}
