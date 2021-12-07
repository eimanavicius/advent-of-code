package com.telesoftas.adventofcode.jurassicjigsaw;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.arraycopy;

public class JurassicJigsaw {

    List<List<Tile>> image;

    public JurassicJigsaw(List<List<Tile>> image) {
        this.image = image;
    }

    public static JurassicJigsaw fromTilesList(List<Tile> tiles) {
        final List<Tile> remainingTiles = new ArrayList<>(tiles);

        List<List<Tile>> image = new ArrayList<>();

        List<Tile> line = null;
        for (
            Tile topTile = remainingTiles.remove(0);
            topTile != null;
            topTile = findTopTileFor(line.get(0), remainingTiles)
        ) {
            line = findLine(remainingTiles, topTile);
            image.add(0, line);
        }

        for (
            Tile bottomTile = findBottomTileFor(image.get(image.size() - 1).get(0), remainingTiles);
            bottomTile != null;
            bottomTile = findBottomTileFor(line.get(0), remainingTiles)
        ) {
            line = findLine(remainingTiles, bottomTile);
            image.add(line);
        }

        return new JurassicJigsaw(image);
    }

    private static Tile findBottomTileFor(Tile tile, List<Tile> remainingTiles) {
        final Tile bottomTile = findTileByRightBorderIn(tile.findBottomBorder(), remainingTiles);
        if (null == bottomTile) {
            return null;
        }
        bottomTile.rotateClockwise();
        bottomTile.flipVertically();
        remainingTiles.remove(bottomTile);
        return bottomTile;
    }

    private static Tile findTopTileFor(Tile tile, List<Tile> remainingTiles) {
        final Tile topTile = findTileByRightBorderIn(tile.findTopBorder(), remainingTiles);
        if (null == topTile) {
            return null;
        }
        topTile.rotateCounterClockwise();
        remainingTiles.remove(topTile);
        return topTile;
    }

    private static List<Tile> findLine(List<Tile> remainingTiles, Tile start) {
        final List<Tile> line = new ArrayList<>();
        Tile next = start;
        line.add(next);
        while (!remainingTiles.isEmpty()) {
            final char[] right = next.findRightBorder();
            next = findTileByRightBorderIn(right, remainingTiles);
            if (next == null) {
                break;
            }
            line.add(next);
            remainingTiles.remove(next);
        }

        next = line.get(0);
        while (!remainingTiles.isEmpty()) {
            final char[] left = next.findLeftBorder();
            next = findTileByLeftBorderIn(left, remainingTiles);
            if (next == null) {
                break;
            }
            line.add(0, next);
            remainingTiles.remove(next);
        }
        return line;
    }

    private static Tile findTileByRightBorderIn(char[] border, List<Tile> tiles) {
        for (Tile tile : tiles) {
            if (tile.tryRotateAndFlipUntilLeftMatch(border)) {
                return tile;
            }
        }
        return null;
    }

    private static Tile findTileByLeftBorderIn(char[] border, List<Tile> tiles) {
        for (Tile tile : tiles) {
            if (tile.tryRotateAndFlipUntilRightMatch(border)) {
                return tile;
            }
        }
        return null;
    }

    public long multiplyCornerTileIds() {
        final List<Tile> firstLine = image.get(0);
        final List<Tile> lastLine = image.get(image.size() - 1);
        return firstLine.get(0).id()
            * firstLine.get(firstLine.size() - 1).id()
            * lastLine.get(0).id()
            * lastLine.get(lastLine.size() - 1).id();
    }

    public int determineWaterRoughness() {
        final Tile tile = joinTilesToMassiveTile();

        int monsters = 0;
        for (Mutator mutation : List.<Mutator>of(
            () -> {
            },
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::flipHorizontally,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::flipVertically,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise,
            tile::rotateClockwise
        )) {
            mutation.mutate();

            monsters = tile.searchFullSeaForMonsters();

            if (monsters > 0) {
                break;
            }
        }

        return tile.roughness() - monsters * 15;
    }

    private Tile joinTilesToMassiveTile() {
        final int size = image.get(0).get(0).map().length - 2;
        final char[][] map = new char[image.size() * size][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[image.size() * size];
        }
        for (int i = 0; i < image.size(); i++) {
            List<Tile> lines = image.get(i);
            for (int j = 0; j < lines.size(); j++) {
                Tile tile = lines.get(j);
                final char[][] map1 = tile.map();
                for (int k = 1; k < map1.length - 1; k++) {
                    final char[] chars = map1[k];
                    arraycopy(map1[k], 1, map[(i * size) + k - 1], j * size, chars.length - 2);
                }
            }
        }
        return new Tile(0, map);
    }

    interface Mutator {

        void mutate();
    }
}
