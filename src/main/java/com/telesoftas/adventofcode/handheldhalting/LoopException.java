package com.telesoftas.adventofcode.handheldhalting;

public class LoopException extends Exception {

    private final int sum;

    public LoopException(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}
