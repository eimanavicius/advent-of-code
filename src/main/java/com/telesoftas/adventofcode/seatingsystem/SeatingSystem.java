package com.telesoftas.adventofcode.seatingsystem;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
public class SeatingSystem {

    @Getter
    byte[] layout;

    int width;

    int height;

    public SeatingSystem(byte[] layout) {
        this.layout = layout;
        width = width();
        height = height(width);
    }

    public int modelOccupiedSeats() {
        boolean changed = true;
        while (changed) {
            changed = modelChange();
        }

        return countOccupiedSeats();
    }

    int countOccupiedSeats() {
        int count = 0;
        for (byte occupation : layout) {
            if ('#' == occupation) {
                count++;
            }
        }

        return count;
    }

    boolean modelChange() {
        boolean changed = false;
        byte[] newLayout = Arrays.copyOf(layout, layout.length);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                int seat = seat(column, row);
                final char occupation = (char) layout[seat];
                if ('L' == occupation && 0 == occupiedAdjacentSeats(column, row)) {
                    newLayout[seat] = '#';
                    changed = true;
                } else if ('#' == occupation && occupiedAdjacentSeats(column, row) >= 4) {
                    newLayout[seat] = 'L';
                    changed = true;
                } else if ('.' == occupation) {
                    // nothing
                }
            }
        }
        layout = newLayout;
        return changed;
    }

    private int occupiedAdjacentSeats(int column, int row) {
        int count = 0;
        // left
        if (column != 0 && '#' == layout[seat(column - 1, row)]) {
            count++;
        }
        // right
        if (column < (width - 1) && '#' == layout[seat(column + 1, row)]) {
            count++;
        }
        // top
        if (row != 0 && '#' == layout[seat(column, row - 1)]) {
            count++;
        }
        // bottom
        if (row < (height - 1) && '#' == layout[seat(column, row + 1)]) {
            count++;
        }
        // top-left
        if (row != 0 && column != 0 && '#' == layout[seat(column - 1, row - 1)]) {
            count++;
        }
        // top-right
        if (row != 0 && column < (width - 1) && '#' == layout[seat(column + 1, row - 1)]) {
            count++;
        }
        // bottom-left
        if (row < (height - 1) && column != 0 && '#' == layout[seat(column - 1, row + 1)]) {
            count++;
        }
        // bottom-right
        if (row < (height - 1) && column < (width - 1) && '#' == layout[seat(column + 1, row + 1)]) {
            count++;
        }
        return count;
    }

    int seat(int column, int row) {
        return row * (width + 1) + column;
    }

    int seat(int... columnAndRow) {
        return columnAndRow[1] * (width + 1) + columnAndRow[0];
    }


    private int height(int width) {
        for (int i = layout.length - 1; i >= 0; i--) {
            if (layout[i] == '\n') {
                return (i / (width + 1)) + 1;
            }
        }
        throw new RuntimeException();
    }

    private int width() {
        for (int i = 0; i < layout.length; i++) {
            if (layout[i] == '\n') {
                return i;
            }
        }
        throw new RuntimeException();
    }
}
