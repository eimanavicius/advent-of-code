package com.telesoftas.adventofcode.jurassicjigsaw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

    @Test
    void verticalFlip() {
        final Tile tile = new Tile(1, new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}});

        tile.flipVertically();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{{'3', '2', '1'}, {'6', '5', '4'}, {'9', '8', '7'}})));
    }

    @Test
    void horizontalFlip() {
        final Tile tile = new Tile(1, new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}});

        tile.flipHorizontally();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{{'7', '8', '9'}, {'4', '5', '6'}, {'1', '2', '3'}})));
    }

    @Test
    void rotate_clockwise() {
        final Tile tile = new Tile(1, new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}});

        tile.rotateClockwise();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{{'7', '4', '1'}, {'8', '5', '2'}, {'9', '6', '3'}})));
    }

    @Test
    void rotate_clockwise_bigger() {
        final Tile tile = new Tile(1, new char[][]{
            {'1', '2', '3', '4'},
            {'5', '6', '7', '8'},
            {'9', 'a', 'b', 'c'},
            {'d', 'e', 'f', 'g'}
        });

        tile.rotateClockwise();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{
            {'d', '9', '5', '1'},
            {'e', 'a', '6', '2'},
            {'f', 'b', '7', '3'},
            {'g', 'c', '8', '4'}
        })));
    }

    @Test
    void rotate_counter_clockwise() {
        final Tile tile = new Tile(1, new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}});

        tile.rotateCounterClockwise();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{{'3', '6', '9'}, {'2', '5', '8'}, {'1', '4', '7'}})));
    }

    @Test
    void rotate_counter_clockwise_bigger() {
        final Tile tile = new Tile(1, new char[][]{
            {'1', '2', '3', '4'},
            {'5', '6', '7', '8'},
            {'9', 'a', 'b', 'c'},
            {'d', 'e', 'f', 'g'}
        });

        tile.rotateCounterClockwise();

        assertTrue(tile.equivalent(new Tile(1, new char[][]{
            {'4', '8', 'c', 'g'},
            {'3', '7', 'b', 'f'},
            {'2', '6', 'a', 'e'},
            {'1', '5', '9', 'd'}
        })));
    }


    @Test
    void one_sea_monster() {
        char[][] map = {
            "                  # ".toCharArray(),
            "#    ##    ##    ###".toCharArray(),
            " #  #  #  #  #  #   ".toCharArray()
        };

        assertTrue(new Tile(0, map).isSeaMonster(0, 0));
    }

    @Test
    void one_sea_monster_with_offset() {
        char[][] map = {
            "                    # ".toCharArray(),
            "                    # ".toCharArray(),
            "  #    ##    ##    ###".toCharArray(),
            "   #  #  #  #  #  #   ".toCharArray()
        };

        assertTrue(new Tile(0, map).isSeaMonster(1, 2));
    }

    @Test
    void no_sea_monster() {
        char[][] map = {
            "                    ".toCharArray(),
            "#    ##    ##    ###".toCharArray(),
            " #  #  #  #  #  #   ".toCharArray()
        };

        assertFalse(new Tile(0, map).isSeaMonster(0, 0));
    }
}
