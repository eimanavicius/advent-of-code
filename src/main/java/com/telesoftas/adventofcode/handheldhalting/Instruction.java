package com.telesoftas.adventofcode.handheldhalting;

public record Instruction(String command, int value) {

    public Instruction withCommand(String command) {
        return new Instruction(command, value);
    }

}
