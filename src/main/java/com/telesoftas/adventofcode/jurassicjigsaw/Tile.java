package com.telesoftas.adventofcode.jurassicjigsaw;

import static java.util.Arrays.compare;

public record Tile(long id, char[][] map) {

    public char[] findTopBorder() {
        return map[0];
    }

    public char[] findBottomBorder() {
        return map[map.length - 1];
    }

    char[] findRightBorder() {
        final int lastIndex = map.length - 1;
        char[] right = new char[map.length];
        for (int i = 0; i <= lastIndex; i++) {
            right[i] = map[i][lastIndex];
        }
        return right;
    }

    char[] findLeftBorder() {
        char[] left = new char[map.length];
        for (int i = 0; i < map.length; i++) {
            left[i] = map[i][0];
        }
        return left;
    }

    public boolean tryRotateAndFlipUntilRightMatch(char[] border) {
        final int last = map.length - 1;

        char[] top = map[0];

        // try top
        if (0 == compare(top, border)) {
            rotateClockwise();
            return true;
        }

        char[] bottom = map[last];
        // try bottom
        if (0 == compare(bottom, border)) {
            flipVertically();
            rotateCounterClockwise();
            return true;
        }

        char[] left = new char[map.length];
        char[] right = new char[map.length];
        for (int i = 0; i <= last; i++) {
            left[i] = map[i][0];
            right[i] = map[i][last];
        }

        // try left
        if (0 == compare(left, border)) {
            flipVertically();
            return true;
        }

        // try right
        if (0 == compare(right, border)) {
            return true;
        }

        // try top flip
        if (0 == compare(flip(top), border)) {
            flipVertically();
            rotateClockwise();
            return true;
        }

        // try bottom flip
        if (0 == compare(flip(bottom), border)) {
            flipVertically();
            rotateCounterClockwise();
            return true;
        }

        // try left flip
        if (0 == compare(flip(left), border)) {
            flipVertically();
            flipHorizontally();
            return true;
        }

        // try right flip
        if (0 == compare(flip(right), border)) {
            flipHorizontally();
            return true;
        }

        return false;
    }

    public boolean tryRotateAndFlipUntilLeftMatch(char[] border) {
        final int last = map.length - 1;

        char[] top = map[0];

        // try match top
        if (0 == compare(top, border)) {
            flipVertically();
            rotateCounterClockwise();
            return true;
        }

        char[] bottom = map[last];
        // try match bottom
        if (0 == compare(bottom, border)) {
            rotateClockwise();
            // ?
            return true;
        }

        char[] left = new char[map.length];
        char[] right = new char[map.length];
        for (int i = 0; i <= last; i++) {
            left[i] = map[i][0];
            right[i] = map[i][last];
        }

        // try left
        if (0 == compare(left, border)) {
            return true;
        }

        // try right
        if (0 == compare(right, border)) {
            flipVertically();
            return true;
        }

        // try top flip
        if (0 == compare(flip(top), border)) {
            rotateCounterClockwise();
            return true;
        }

        // try bottom flip
        if (0 == compare(flip(bottom), border)) {
            rotateClockwise();
            flipHorizontally();
            return true;
        }

        // try left flip
        if (0 == compare(flip(left), border)) {
            flipHorizontally();
            return true;
        }

        // try right flip
        if (0 == compare(flip(right), border)) {
            flipHorizontally();
            flipVertically();
            return true;
        }

        return false;
    }

    public void flipHorizontally() {
        final int last = map.length - 1;
        for (int i = 0; i < map.length / 2; i++) {
            char[] temp = map[i];
            map[i] = map[last - i];
            map[last - i] = temp;
        }
    }

    public void flipVertically() {
        final int last = map.length - 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length / 2; j++) {
                char t = map[i][j];
                map[i][j] = map[i][last - j];
                map[i][last - j] = t;
            }
        }
    }

    public void rotateClockwise() {
        final int half = map.length / 2;
        final int last = map.length - 1;
        for (int i = 0; i <= half; i++) {
            for (int j = i; j < last - i; j++) {
                char temp = map[i][j];
                map[i][j] = map[last - j][i];
                map[last - j][i] = map[last - i][last - j];
                map[last - i][last - j] = map[j][last - i];
                map[j][last - i] = temp;
            }
        }
    }

    public void rotateCounterClockwise() {
        final int half = map.length / 2;
        final int last = map.length - 1;
        for (int i = 0; i < half; i++) {
            for (int j = i; j < last - i; j++) {
                char temp = map[i][j];
                map[i][j] = map[j][last - i];
                map[j][last - i] = map[last - i][last - j];
                map[last - i][last - j] = map[last - j][i];
                map[last - j][i] = temp;
            }
        }
    }

    private char[] flip(char[] input) {
        final char[] flipped = new char[input.length];
        for (int i = input.length - 1, j = 0; i >= 0; i--, j++) {
            flipped[j] = input[i];
        }
        return flipped;
    }

    public boolean equivalent(Tile tile) {
        for (int i = 0; i < map.length; i++) {
            if (0 != compare(tile.map[i], map[i])) {
                return false;
            }
        }
        return true;
    }
}
