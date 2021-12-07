package com.telesoftas.adventofcode.seatingsystem;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
public class SeatingSystem {

    public static final char TAKEN = '#';
    public static final char EMPTY = 'L';
    public static final char FLOOR = '.';
    @Getter
    byte[] layout;

    int width;

    int height;

    public SeatingSystem(byte[] layout) {
        this.layout = layout;
        width = width();
        height = height(width);
    }

    public int countOccupiedSeats() {
        int count = 0;
        for (byte occupation : layout) {
            if (TAKEN == occupation) {
                count++;
            }
        }

        return count;
    }

    public int modelByOccupiedImmediatelyAdjacentSeats() {
        boolean changed = true;
        while (changed) {
            changed = modelChangeByImmediateAdjacency();
        }

        return countOccupiedSeats();
    }

    boolean modelChangeByImmediateAdjacency() {
        boolean changed = false;
        byte[] newLayout = Arrays.copyOf(layout, layout.length);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                int seat = seat(column, row);
                final char occupation = (char) layout[seat];
                if (EMPTY == occupation && 0 == immediatelyAdjacentSeats(column, row, TAKEN)) {
                    newLayout[seat] = TAKEN;
                    changed = true;
                } else if (TAKEN == occupation && immediatelyAdjacentSeats(column, row, TAKEN) >= 4) {
                    newLayout[seat] = EMPTY;
                    changed = true;
                } else if (FLOOR == occupation) {
                    // nothing
                }
            }
        }
        layout = newLayout;
        return changed;
    }

    public int modelOccupiedFarAdjacentSeats() {
        boolean changed = true;
        while (changed) {
            changed = modelChangeByFarAdjacency();
        }

        return countOccupiedSeats();
    }

    boolean modelChangeByFarAdjacency() {
        boolean changed = false;
        byte[] newLayout = Arrays.copyOf(layout, layout.length);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                int seat = seat(column, row);
                final char occupation = (char) layout[seat];
                if (EMPTY == occupation
                    && (8 == immediatelyAdjacentSeats(column, row, FLOOR)
                    || 0 == farAdjacentSeats(column, row))) {
                    newLayout[seat] = TAKEN;
                    changed = true;
                } else if (TAKEN == occupation && farAdjacentSeats(column, row) >= 5) {
                    newLayout[seat] = EMPTY;
                    changed = true;
                } else if (FLOOR == occupation) {
                    // nothing
                }
            }
        }
        layout = newLayout;
        return changed;
    }

    private int immediatelyAdjacentSeats(int column, int row, char state) {
        int count = 0;
        // left
        if (column != 0 && state == layout[seat(column - 1, row)]) {
            count++;
        }
        // right
        if (column < (width - 1) && state == layout[seat(column + 1, row)]) {
            count++;
        }
        // top
        if (row != 0 && state == layout[seat(column, row - 1)]) {
            count++;
        }
        // bottom
        if (row < (height - 1) && state == layout[seat(column, row + 1)]) {
            count++;
        }
        // top-left
        if (row != 0 && column != 0 && state == layout[seat(column - 1, row - 1)]) {
            count++;
        }
        // top-right
        if (row != 0 && column < (width - 1) && state == layout[seat(column + 1, row - 1)]) {
            count++;
        }
        // bottom-left
        if (row < (height - 1) && column != 0 && state == layout[seat(column - 1, row + 1)]) {
            count++;
        }
        // bottom-right
        if (row < (height - 1) && column < (width - 1) && state == layout[seat(column + 1, row + 1)]) {
            count++;
        }
        return count;
    }

    private int farAdjacentSeats(int column, int row) {
        int count = 0;
        // left
        count += farLeft(column, row);
        // right
        count += farRight(column, row);
        // top
        count += farTop(column, row);
        // bottom
        count += farBottom(column, row);
        // top-left
        count += farTopLeft(column, row);
        // top-right
        count += farTopRight(column, row);
        // bottom-left
        count += farBottomLeft(column, row);
        // bottom-right
        count += farBottomRight(column, row);
        return count;
    }

    private int farBottomRight(int column, int row) {
        while (row < (height - 1) && column < (width - 1)) {
            final byte state = layout[seat(++column, ++row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farBottomLeft(int column, int row) {
        while (row < (height - 1) && column != 0) {
            final byte state = layout[seat(--column, ++row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farTopRight(int column, int row) {
        while (row != 0 && column < (width - 1)) {
            final byte state = layout[seat(++column, --row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farTopLeft(int column, int row) {
        while (row != 0 && column != 0) {
            final byte state = layout[seat(--column, --row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farBottom(int column, int row) {
        while (row < (height - 1)) {
            final byte state = layout[seat(column, ++row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farTop(int column, int row) {
        while (row != 0) {
            final byte state = layout[seat(column, --row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farRight(int column, int row) {
        while (column < (width - 1)) {
            final byte state = layout[seat(++column, row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
    }

    private int farLeft(int column, int row) {
        while (column != 0) {
            final byte state = layout[seat(--column, row)];
            if (TAKEN == state) {
                return 1;
            } else if (EMPTY == state) {
                break;
            }
        }
        return 0;
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
