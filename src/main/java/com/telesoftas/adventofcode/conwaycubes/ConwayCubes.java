package com.telesoftas.adventofcode.conwaycubes;

import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;

public class ConwayCubes {

    private final byte[][][] grid;

    public ConwayCubes(byte[][][] grid) {
        this.grid = grid;
    }

    private static int findActiveNeighbors(byte[][][] grid, int x, int y, int z) {
        int sum = 0;
        final int zSize = grid.length;
        final int ySize = grid[z].length;
        final int xSize = grid[z][y].length;
        for (int i : new int[]{-1, 0, 1}) {
            if (0 > z + i || z + i >= zSize) {
                continue;
            }
            for (int j : new int[]{-1, 0, 1}) {
                if (0 > y + j || y + j >= ySize) {
                    continue;
                }
                for (int k : new int[]{-1, 0, 1}) {
                    if (0 > x + k || x + k >= xSize
                        || (i == 0 && j == 0 && k == 0)
                    ) {
                        continue;
                    }
                    if ('#' == grid[z + i][y + j][x + k]) {
                        sum += 1;
                    }
                }
            }
        }
        return sum;
    }

    private static byte[][][] expand(byte[][][] grid) {
        byte[][][] newGrid = new byte[grid.length + 2][][];
        final int dimension = grid[0].length + 2;
        int z = 0;
        newGrid[z++] = inactiveCubesSlice(dimension);
        for (byte[][] slice : grid) {
            byte[][] newSlice = new byte[dimension][];
            int y = 0;
            newSlice[y++] = inactiveCubesLine(dimension);
            for (byte[] cubes : slice) {
                newSlice[y++] = expandCubesLine(dimension, cubes);
            }
            newSlice[y] = inactiveCubesLine(dimension);
            newGrid[z++] = newSlice;
        }
        newGrid[z] = inactiveCubesSlice(dimension);
        return newGrid;
    }

    private static byte[][] inactiveCubesSlice(int dimension) {
        byte[][] newSlice = new byte[dimension][];
        for (int i = 0; i < dimension; i++) {
            newSlice[i] = inactiveCubesLine(dimension);
        }
        return newSlice;
    }

    private static byte[] inactiveCubesLine(int dimension) {
        final byte[] cubes = new byte[dimension];
        fill(cubes, (byte) '.');
        return cubes;
    }

    private static byte[] expandCubesLine(int dimension, byte[] cubes) {
        final byte[] newLine = new byte[dimension];
        arraycopy(cubes, 0, newLine, 1, cubes.length);
        newLine[0] = '.';
        newLine[dimension - 1] = '.';
        return newLine;
    }

    public int activeCubesAfterCycles(int cycles) {
        ConwayCubes cubes = this;
        for (int cycle = 0; cycle < cycles; cycle++) {
            cubes = cubes.executeBootUp();
        }
        return cubes.activeCubes();
    }

    public ConwayCubes executeBootUp() {
        byte[][][] newGrid = expand(grid);
        byte[][][] snapshot = expand(grid);
        for (int z = 0; z < snapshot.length; z++) {
            for (int y = 0; y < snapshot[z].length; y++) {
                for (int x = 0; x < snapshot[z][y].length; x++) {
                    int activeNeighbors = findActiveNeighbors(snapshot, x, y, z);

                    final byte cube = snapshot[z][y][x];
                    if (cube == '#' && !(activeNeighbors == 2 || activeNeighbors == 3)) {
                        newGrid[z][y][x] = '.';
                    } else if (cube == '.' && activeNeighbors == 3) {
                        newGrid[z][y][x] = '#';
                    }
                }
            }
        }
        return new ConwayCubes(newGrid);
    }

    public int activeCubes() {
        int sum = 0;
        for (byte[][] slices : grid) {
            for (byte[] cubes : slices) {
                for (byte cube : cubes) {
                    if (cube == '#') {
                        sum += 1;
                    }
                }
            }
        }
        return sum;
    }
}
