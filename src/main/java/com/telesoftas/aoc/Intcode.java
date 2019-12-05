package com.telesoftas.aoc;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;

public class Intcode {

    public static void main(String[] args) {
        String input = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,9,19,23,2,23,13,27,1,27,9,31,2,31,6,35,1,5,35,39,1,10,39,43,2,43,6,47,1,10,47,51,2,6,51,55,1,5,55,59,1,59,9,63,1,13,63,67,2,6,67,71,1,5,71,75,2,6,75,79,2,79,6,83,1,13,83,87,1,9,87,91,1,9,91,95,1,5,95,99,1,5,99,103,2,13,103,107,1,6,107,111,1,9,111,115,2,6,115,119,1,13,119,123,1,123,6,127,1,127,5,131,2,10,131,135,2,135,10,139,1,13,139,143,1,10,143,147,1,2,147,151,1,6,151,0,99,2,14,0,0";

        // [0-99]
        int noun = 12;
        int verb = 2;

        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                Integer[] memory = intcode(input, i, j);
                if (memory[0] == 19690720) {
                    System.out.printf("%d %d \n", i, j); // 19690720
                    return;
                }
            }
        }

    }

    private static Integer[] intcode(final String input, final int noun, final int verb) {
        Integer[] mem = Arrays.stream(
            input
                .split(","))
            .map(Integer::parseInt)
            .collect(toList())
            .toArray(Integer[]::new);
        mem[1] = noun;
        mem[2] = verb;

        for (int i = 0; i < mem.length; i += 4) {
            var command = mem[i];
            if (command.equals(1)) {
                mem[mem[i + 3]] = mem[mem[i + 1]] + mem[mem[i + 2]];
            } else if (command.equals(2)) {
                mem[mem[i + 3]] = mem[mem[i + 1]] * mem[mem[i + 2]];
            } else if (command.equals(99)) {
                break;
            }
        }
        System.out.println(Arrays.toString(mem));
        return mem;
    }
}
