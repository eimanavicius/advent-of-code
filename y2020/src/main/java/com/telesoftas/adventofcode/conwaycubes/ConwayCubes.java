package com.telesoftas.adventofcode.conwaycubes;

import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;

public class ConwayCubes {

    private final byte[][][][] grid;

    public ConwayCubes(byte[][][][] grid) {
        this.grid = grid;
    }

    public ConwayCubes(byte[][][] grid) {
        this(new byte[][][][]{grid});
    }

    public ConwayCubes(byte[][] grid) {
        this(new byte[][][]{grid});
    }

    private static int findActiveNeighbors(byte[][][][] grid, int x, int y, int z, int w) {
        int sum = 0;
        final int wSize = grid.length;
        final int zSize = grid[w].length;
        final int ySize = grid[w][z].length;
        final int xSize = grid[w][z][y].length;
        for (int i : new int[]{-1, 0, 1}) {
            if (0 > w + i || w + i >= wSize) {
                continue;
            }
            for (int j : new int[]{-1, 0, 1}) {
                if (0 > z + j || z + j >= zSize) {
                    continue;
                }
                for (int k : new int[]{-1, 0, 1}) {
                    if (0 > y + k || y + k >= ySize) {
                        continue;
                    }
                    for (int l : new int[]{-1, 0, 1}) {
                        if (0 > x + l || x + l >= xSize
                            || (i == 0 && j == 0 && k == 0 && l == 0)
                        ) {
                            continue;
                        }
                        if ('#' == grid[w + i][z + j][y + k][x + l]) {
                            sum += 1;
                        }
                    }
                }
            }
        }
        return sum;
    }

    private static byte[][][][] expand(byte[][][][] grid) {
        final int d4Size = grid.length + 2;
        final int d3Size = grid[0].length + 2;
        final int d2Size = grid[0][0].length + 2;
        byte[][][][] d4 = new byte[d4Size][][][];

        int w = 0;
        d4[w++] = inactiveCubesSliceGrid(d3Size, d2Size);

        for (byte[][][] d3 : grid) {
            byte[][][] newD3 = new byte[d3Size][][];
            int z = 0;
            newD3[z++] = inactiveCubesSlice(d2Size);
            for (byte[][] slice : d3) {
                byte[][] newSlice = new byte[d2Size][];
                int y = 0;
                newSlice[y++] = inactiveCubesLine(d2Size);
                for (byte[] cubes : slice) {
                    newSlice[y++] = expandCubesLine(d2Size, cubes);
                }
                newSlice[y] = inactiveCubesLine(d2Size);
                newD3[z++] = newSlice;
            }
            newD3[z] = inactiveCubesSlice(d2Size);
            d4[w++] = newD3;
        }

        d4[w] = inactiveCubesSliceGrid(d3Size, d2Size);

        return d4;
    }

    private static byte[][][] inactiveCubesSliceGrid(int d3, int d2) {
        byte[][][] newSlice = new byte[d3][][];
        for (int i = 0; i < d3; i++) {
            newSlice[i] = inactiveCubesSlice(d2);
        }
        return newSlice;
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
        byte[][][][] newGrid = expand(grid);
        byte[][][][] snapshot = expand(grid);
        for (int w = 0; w < snapshot.length; w++) {
            for (int z = 0; z < snapshot[w].length; z++) {
                for (int y = 0; y < snapshot[w][z].length; y++) {
                    for (int x = 0; x < snapshot[w][z][y].length; x++) {
                        int activeNeighbors = findActiveNeighbors(snapshot, x, y, z, w);

                        final byte cube = snapshot[w][z][y][x];
                        if (cube == '#' && !(activeNeighbors == 2 || activeNeighbors == 3)) {
                            newGrid[w][z][y][x] = '.';
                        } else if (cube == '.' && activeNeighbors == 3) {
                            newGrid[w][z][y][x] = '#';
                        }
                    }
                }
            }
        }
        return new ConwayCubes(newGrid);
    }

    public int activeCubes() {
        int sum = 0;
        for (byte[][][] d3 : grid) {
            for (byte[][] d2 : d3) {
                for (byte[] cubes : d2) {
                    for (byte cube : cubes) {
                        if (cube == '#') {
                            sum += 1;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
