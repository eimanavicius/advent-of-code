package com.telesoftas.aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntcodeTest {

    private Intcode.Output output;

    @BeforeEach
    void setUp() {
        output = new Intcode.Output();
    }

    @Test
    void pase_addition_instruction() {
        String input = "1,1,2,0,99";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), output);

        assertEquals(3, memory[0]);
    }

    @Test
    void parse_output_instruction() {
        // Opcode 4 outputs the value of its only parameter. For example, the instruction 4,50 would output the value at address 50.
        Integer[] program = Intcode.parseIntcodeInput("4,2,99");

        Integer[] memory = Intcode.intcode(program, output);

        assertEquals(99, output.getValue());
    }

    @Test
    void golden_master() {
        String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,9,19,23,2,23,13,27,1,27,9,31,2,31,6,35,1,5,35,39,1,10,39,43,2,43,6,47,1,10,47,51,2,6,51,55,1,5,55,59,1,59,9,63,1,13,63,67,2,6,67,71,1,5,71,75,2,6,75,79,2,79,6,83,1,13,83,87,1,9,87,91,1,9,91,95,1,5,95,99,1,5,99,103,2,13,103,107,1,6,107,111,1,9,111,115,2,6,115,119,1,13,119,123,1,123,6,127,1,127,5,131,2,10,131,135,2,135,10,139,1,13,139,143,1,10,143,147,1,2,147,151,1,6,151,0,99,2,14,0,0";

        Integer[] memory = Intcode.intcode(Intcode.parseIntcodeInput(input), output);

        assertEquals(3306701, memory[0]);
    }
}
