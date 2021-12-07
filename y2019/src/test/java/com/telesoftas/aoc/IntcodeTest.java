package com.telesoftas.aoc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.telesoftas.aoc.Intcode.Opcode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntcodeTest {

    private Intcode.Output output;

    @BeforeEach
    void setUp() {
        output = new Intcode.Output();
    }

    @Test
    void parse_addition_instruction() {
        String program = "1,1,2,0,99";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(program), 0, output);

        assertEquals(3, memory[0]);
    }

    @Test
    void pase_input_instruction() {
        // Opcode 3 takes a single integer as input and saves it to the position given by its only parameter. For example, the instruction 3,50 would take an input value and store it at address 50.
        String program = "3,0,99";
        int input = 1;

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(program), input, output);

        assertArrayEquals(new Integer[]{input, 0, 99}, memory);
    }

    @Test
    void parse_opcode_with_mode_2() {
        /*
ABCDE
 1002

DE - two-digit opcode,      02 == opcode 2
 C - mode of 1st parameter,  0 == position mode
 B - mode of 2nd parameter,  1 == immediate mode
 A - mode of 3rd parameter,  0 == position mode,
                                  omitted due to being a leading zero
         */
        String program = "1002,2,3,0";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(program), 0, output);

        assertEquals(9, memory[0]);
    }

    @Test
    void parse_opcode_with_mode_1() {

        String program = "1001,2,3,0";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(program), 0, output);

        assertEquals(6, memory[0]);
    }

    @Test
    void parse_opcode_with_mode_4() {

        String program = "104,5";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(program), 0, output);

        assertEquals(5, output.getValue());
    }

    @Test
    void parse_opcode_with_default_position_modes() {
        Opcode opcode = Intcode.parseOpcode(1);

        assertAll(
            () -> assertEquals(1, opcode.getOperation()),
            () -> assertEquals(0, opcode.getModeFirst()),
            () -> assertEquals(0, opcode.getModeSecond()),
            () -> assertEquals(0, opcode.getModeThird())
        );
    }

    @Test
    void parse_opcode_with_first_param_in_immediate_mode() {
        Opcode opcode = Intcode.parseOpcode(101);

        assertAll(
            () -> assertEquals(1, opcode.getOperation()),
            () -> assertEquals(1, opcode.getModeFirst()),
            () -> assertEquals(0, opcode.getModeSecond()),
            () -> assertEquals(0, opcode.getModeThird())
        );
    }

    @Test
    void parse_opcode_with_second_param_in_immediate() {
        Opcode opcode = Intcode.parseOpcode(1001);

        assertAll(
            () -> assertEquals(1, opcode.getOperation(), "op"),
            () -> assertEquals(0, opcode.getModeFirst(), "first"),
            () -> assertEquals(1, opcode.getModeSecond(), "second"),
            () -> assertEquals(0, opcode.getModeThird(), "third")
        );
    }

    @Test
    void parse_output_instruction() {
        // Opcode 4 outputs the value of its only parameter. For example, the instruction 4,50 would output the value at address 50.
        Integer[] program = Intcode.parseIntcodeInput("4,2,99");

        Integer[] memory = Intcode.intcode(program, 0, output);

        assertEquals(99, output.getValue());
    }

    @Test
    void golden_master() {
        String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,9,19,23,2,23,13,27,1,27,9,31,2,31,6,35,1,5,35,39,1,10,39,43,2,43,6,47,1,10,47,51,2,6,51,55,1,5,55,59,1,59,9,63,1,13,63,67,2,6,67,71,1,5,71,75,2,6,75,79,2,79,6,83,1,13,83,87,1,9,87,91,1,9,91,95,1,5,95,99,1,5,99,103,2,13,103,107,1,6,107,111,1,9,111,115,2,6,115,119,1,13,119,123,1,123,6,127,1,127,5,131,2,10,131,135,2,135,10,139,1,13,139,143,1,10,143,147,1,2,147,151,1,6,151,0,99,2,14,0,0";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), 0, output);

        assertEquals(3306701, memory[0]);
    }

    @Test
    void golden_master_2() {
        String input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                       "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                       "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), 1, output);

        assertEquals(999, output.getValue());
    }

    @Test
    void golden_master_3() {
        String input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                       "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                       "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), 8, output);

        assertEquals(1000, output.getValue());
    }

    @Test
    void golden_master_4() {
        String input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                       "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                       "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), 9, output);

        assertEquals(1001, output.getValue());
    }

    @Test
    void opcode_5_instruction() {
// Opcode 5 is jump-if-true:
// if the first parameter is non-zero,
//     it sets the instruction pointer to the value from the second parameter.
//     Otherwise, it does nothing.

        Integer[] mem = new Integer[]{5, 0, 1};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        assertEquals(0, Intcode.executeJumpIfTrue(mem, pointer, opcode));
    }

    @Test
    void opcode_5_instruction_2() {
// Opcode 5 is jump-if-true:
// if the first parameter is non-zero,
//     it sets the instruction pointer to the value from the second parameter.
//     Otherwise, it does nothing.

        Integer[] mem = new Integer[]{5, 0, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        assertEquals(5, Intcode.executeJumpIfTrue(mem, pointer, opcode));
    }

    @Test
    void opcode_5_instruction_otherwise() {

        Integer[] mem = new Integer[]{5, 2, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        assertEquals(3, Intcode.executeJumpIfTrue(mem, pointer, opcode));
    }

    // Opcode 6 is jump-if-false:
    // if the first parameter is zero,
    //     it sets the instruction pointer to the value from the second parameter.
    //     Otherwise, it does nothing.

    @Test
    void opcode_6_instruction() {
        // ARRANGE
        Integer[] mem = new Integer[]{1106, 0, 1};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int actual = Intcode.executeJumpIfFalse(mem, pointer, opcode);

        // ASSERT
        assertEquals(1, actual);
    }

    @Test
    void opcode_6_instruction_return_next_opcode_pos() {
        Integer[] mem = new Integer[]{1106, 1, 1};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int actual = Intcode.executeJumpIfFalse(mem, pointer, opcode);

        // ASSERT
        assertEquals(3, actual);
    }

    @Test
    void opcode_7_instruction_when_first_less_than_second_write_1_to_third() {
        Integer[] mem = new Integer[]{1107, 0, 1, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int actual = Intcode.executeLessThan(mem, pointer, opcode);

        // ASSERT
        assertAll(
            () -> assertEquals(1, mem[0], "calc result"),
            () -> assertEquals(4, actual, "new position")
        );
    }

    // Opcode 7 is less than:
    // if the first parameter is less than the second parameter,
    //     it stores 1 in the position given by the third parameter.
    //     Otherwise, it stores 0.
    @Test
    void opcode_7_instruction_when_first_equal_than_second_write_0_to_third() {
        Integer[] mem = new Integer[]{1107, 1, 1, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int actual = Intcode.executeLessThan(mem, pointer, opcode);

        // ASSERT
        assertAll(
            () -> assertEquals(0, mem[0], "calc result"),
            () -> assertEquals(4, actual, "new position")
        );
    }

    @Test
    void opcode_7_instruction_when_first_greater_than_second_write_0_to_third() {
        Integer[] mem = new Integer[]{1107, 1, 0, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int actual = Intcode.executeLessThan(mem, pointer, opcode);

        // ASSERT
        assertAll(
            () -> assertEquals(0, mem[0], "calc result"),
            () -> assertEquals(4, actual, "new position")
        );
    }

    // Opcode 8 is equals:
    // if the first parameter is equal to the second parameter,
    //   it stores 1 in the position given by the third parameter.
    //   Otherwise, it stores 0.
    @Test
    void opcode_8_instruction_when_first_equal_to_second_write_1_to_third() {
        Integer[] mem = new Integer[]{1108, 1, 1, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int newPosition = Intcode.executeEqualTo(mem, pointer, opcode);

        // ASSERT
        assertAll(
            () -> assertEquals(1, mem[0], "calc result"),
            () -> assertEquals(4, newPosition, "new position")
        );
    }

    @Test
    void opcode_8_instruction_when_first_not_equal_to_second_write_0_to_third() {
        Integer[] mem = new Integer[]{1108, 1, 5, 0};
        int pointer = 0;
        Opcode opcode = Intcode.parseOpcode(mem[0]);

        // ACT
        int newPosition = Intcode.executeEqualTo(mem, pointer, opcode);

        // ASSERT
        assertAll(
            () -> assertEquals(0, mem[0], "calc result"),
            () -> assertEquals(4, newPosition, "new position")
        );
    }
}
