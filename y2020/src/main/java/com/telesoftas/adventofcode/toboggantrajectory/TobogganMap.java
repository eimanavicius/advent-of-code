package com.telesoftas.adventofcode.toboggantrajectory;

import lombok.Value;

import java.util.List;

@Value
public class TobogganMap {

    byte[] map;

    int width;

    int height;

    public TobogganMap(byte[] map) {
        this.map = map;
        width = width();
        height = height(width);
    }

    public long multipliedTreesOnAllSlopes(List<Slope> slopes) {
        long trees = 1;

        for (Slope slope : slopes) {
            trees *= countEncounteredTrees(slope);
        }

        return trees;
    }

    public int countEncounteredTrees(Slope slope) {
        int trees = 0;

        for (var pos = new Position(0, 0); pos.notAtBottom(); pos = pos.jump(slope)) {
            if (isTree(pos.positionOnMap())) {
                trees++;
            }
        }

        return trees;
    }

    private boolean isTree(int position) {
        return '#' == map[position];
    }

    private int height(int width) {
        for (int i = map.length - 1; i >= 0; i--) {
            if (map[i] == '\n') {
                return (i / (width + 1)) + 1;
            }
        }
        throw new RuntimeException();
    }

    private int width() {
        for (int i = 0; i < map.length; i++) {
            if (map[i] == '\n') {
                return i;
            }
        }
        throw new RuntimeException();
    }

    @Value
    private class Position {

        int x;

        int y;

        Position jump(Slope slope) {
            return new Position(
                (x + slope.getRight()) % width,
                y + slope.getDown()
            );
        }

        boolean notAtBottom() {
            return y < height;
        }

        int positionOnMap() {
            return y * (width + 1) + x;
        }
    }
}
