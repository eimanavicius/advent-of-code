package com.telesoftas.aoc;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;

public class Intcode {

    public static void main(String[] args) {
        Output output = new Output();
        String input = "3,225,1,225,6,6,1100,1,238,225,104,0,1001,152,55,224,1001,224,-68,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223,1101,62,41,225,1101,83,71,225,102,59,147,224,101,-944,224,224,4,224,1002,223,8,223,101,3,224,224,1,224,223,223,2,40,139,224,1001,224,-3905,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,6,94,224,101,-100,224,224,4,224,1002,223,8,223,101,6,224,224,1,224,223,223,1102,75,30,225,1102,70,44,224,101,-3080,224,224,4,224,1002,223,8,223,1001,224,4,224,1,223,224,223,1101,55,20,225,1102,55,16,225,1102,13,94,225,1102,16,55,225,1102,13,13,225,1,109,143,224,101,-88,224,224,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1002,136,57,224,101,-1140,224,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,101,76,35,224,1001,224,-138,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,1002,223,2,223,1006,224,329,1001,223,1,223,8,677,226,224,102,2,223,223,1006,224,344,101,1,223,223,1107,226,226,224,1002,223,2,223,1006,224,359,1001,223,1,223,1108,677,226,224,102,2,223,223,1005,224,374,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,389,1001,223,1,223,108,677,677,224,1002,223,2,223,1005,224,404,1001,223,1,223,1007,677,677,224,102,2,223,223,1005,224,419,1001,223,1,223,8,226,677,224,102,2,223,223,1005,224,434,101,1,223,223,1008,677,226,224,102,2,223,223,1006,224,449,1001,223,1,223,7,677,677,224,102,2,223,223,1006,224,464,1001,223,1,223,8,226,226,224,1002,223,2,223,1005,224,479,1001,223,1,223,7,226,677,224,102,2,223,223,1006,224,494,1001,223,1,223,7,677,226,224,1002,223,2,223,1005,224,509,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,524,101,1,223,223,1007,677,226,224,102,2,223,223,1006,224,539,101,1,223,223,107,226,226,224,1002,223,2,223,1006,224,554,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,569,1001,223,1,223,1107,677,226,224,1002,223,2,223,1005,224,584,101,1,223,223,1107,226,677,224,102,2,223,223,1005,224,599,101,1,223,223,1108,226,677,224,102,2,223,223,1005,224,614,101,1,223,223,108,677,226,224,102,2,223,223,1005,224,629,101,1,223,223,107,226,677,224,102,2,223,223,1006,224,644,1001,223,1,223,1108,226,226,224,1002,223,2,223,1006,224,659,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,674,101,1,223,223,4,223,99,226";

        Integer[] inputs = parseIntcodeInput(input);

        Integer[] memory = intcode(inputs, 5, output);

        System.out.println(output.getValue());
    }

    public static Integer[] parseIntcodeInput(final String input) {
        return Arrays.stream(
            input.split(","))
            .map(Integer::parseInt)
            .collect(toList())
            .toArray(Integer[]::new);
    }

    public static Integer[] intcode(final Integer[] mem, final int input, final Output output) {
        label:
        for (int pointer = 0; pointer < mem.length; ) {

            Opcode opcode = parseOpcode(mem[pointer]);

            switch (opcode.getOperation()) {
                case 1:  // addition
                    mem[mem[pointer + 3]] = take(mem, mem[pointer + 1], opcode.getModeFirst())
                                            + take(mem, mem[pointer + 2], opcode.getModeSecond());
                    pointer += 4;
                    break;
                case 2:  // multiplication
                    mem[mem[pointer + 3]] = take(mem, mem[pointer + 1], opcode.getModeFirst())
                                            * take(mem, mem[pointer + 2], opcode.getModeSecond());
                    pointer += 4;
                    break;
                case 3:  // input
                    mem[mem[pointer + 1]] = input;
                    pointer += 2;
                    break;
                case 4:  // output
                    output.write(take(mem, mem[pointer + 1], opcode.getModeFirst()));
                    pointer += 2;
                    break;
                case 5: // jump if true
                    pointer = executeJumpIfTrue(mem, pointer, opcode);
                    break;
                case 6:
                    pointer = executeJumpIfFalse(mem, pointer, opcode);
                    break;
                case 7:
                    pointer = executeLessThan(mem, pointer, opcode);
                    break;
                case 8:
                    pointer = executeEqualTo(mem, pointer, opcode);
                    break;
                case 99:  // halt
                    break label;

                default:
                    throw new RuntimeException(String.format("unhandled opcode %s", opcode.opration));
            }
        }

        return mem;
    }

    public static int executeJumpIfTrue(final Integer[] mem, final int pointer, final Opcode opcode) {
        Integer firstParam = take(mem, mem[pointer + 1], opcode.getModeFirst());
        Integer secondParam = take(mem, mem[pointer + 2], opcode.getModeSecond());

        if (firstParam != 0) {
            return secondParam;
        }

        return pointer + 3; // new pointer
    }

    public static int executeJumpIfFalse(
        final Integer[] mem,
        final int pointer,
        final Opcode opcode
    ) {
        Integer firstParam = take(mem, mem[pointer + 1], opcode.getModeFirst());
        Integer secondParam = take(mem, mem[pointer + 2], opcode.getModeSecond());

        if (firstParam == 0) {
            return secondParam;
        }

        return pointer + 3;
    }

    // Opcode 7 is less than:
    // if the first parameter is less than the second parameter,
    //     it stores 1 in the position given by the third parameter.
    //     Otherwise, it stores 0.
    public static int executeLessThan(final Integer[] mem, final int pointer, final Opcode opcode) {
        Integer firstParam = take(mem, mem[pointer + 1], opcode.getModeFirst());
        Integer secondParam = take(mem, mem[pointer + 2], opcode.getModeSecond());
        Integer thirdParam = mem[pointer + 3];

        if (firstParam < secondParam) {
            mem[thirdParam] = 1;
        } else {
            mem[thirdParam] = 0;
        }

        return pointer + 4;
    }

    // Opcode 8 is equals:
    // if the first parameter is equal to the second parameter,
    //   it stores 1 in the position given by the third parameter.
    //   Otherwise, it stores 0.
    public static int executeEqualTo(final Integer[] mem, final int pointer, final Opcode opcode) {
        int firstParam = take(mem, mem[pointer + 1], opcode.getModeFirst());
        int secondParam = take(mem, mem[pointer + 2], opcode.getModeSecond());
        Integer thirdParam = mem[pointer + 3];

        if (firstParam == secondParam) {
            mem[thirdParam] = 1;
        } else {
            mem[thirdParam] = 0;
        }

        return pointer + 4;
    }

    private static Integer take(final Integer[] mem, final Integer value, final int mode) {
        if (mode == 0) {
            return mem[value];
        }
        return value;
    }

    public static Opcode parseOpcode(final int opcodeWithModes) {
        int operation = opcodeWithModes % 100;
        int modeFirst = opcodeWithModes / 100 % 10;
        int modeSecond = opcodeWithModes / 1000;
        return new Opcode(operation, modeFirst, modeSecond, 0);
    }

    public static class Opcode {

        private final int opration;

        private final int modeFirst;

        private final int modeSecond;

        private final int modeThird;

        public Opcode(final int opration, final int modeFirst, final int modeSecond, final int modeThird) {
            this.opration = opration;
            this.modeFirst = modeFirst;
            this.modeSecond = modeSecond;
            this.modeThird = modeThird;
        }

        public int getOperation() {
            return opration;
        }

        public int getModeFirst() {
            return modeFirst;
        }

        public int getModeSecond() {
            return modeSecond;
        }

        public int getModeThird() {
            return modeThird;
        }
    }

    public static class Output {

        private Integer integer;

        public int getValue() {
            return integer;
        }

        public void write(final Integer integer) {
            this.integer = integer;
        }
    }
}
