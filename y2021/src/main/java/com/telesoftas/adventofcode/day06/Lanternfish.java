package com.telesoftas.adventofcode.day06;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Lanternfish {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day06.txt")) {
            int[] fishes = toNumbersArray(input);

            System.out.println("How many lanternfish would there be after 80 days?");
            System.out.println(amountOfLanternFishAfter(80, fishes));
        }
    }

    public static int amountOfLanternFishAfter(int days, int[] fishes) {
        int amount = 0;
        for (int fish : fishes) {
            amount += singleFishReplication(fish, days);
        }
        return amount;
    }

    public static int singleFishReplication(int fishTimer, int days) {
        int amount = 1;
        for (int i = 0; i < days; i++) {
            fishTimer--;
            if (fishTimer < 0) {
                amount += singleFishReplication(8, days - i - 1);
                fishTimer = 6;
            }
        }
        return amount;
    }

    static int[] toNumbersArray(InputStream input) {
        return new Scanner(input)
            .useDelimiter(",")
            .tokens()
            .mapToInt(Integer::valueOf)
            .toArray();
    }
}
