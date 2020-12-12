package com.telesoftas.adventofcode.rainrisk;

import lombok.Value;
import lombok.With;

@Value
@With
public class Point {

    int x;
    int y;

    public Point left() {
        return new Point(y * -1, x);
    }

    public Point right() {
        return new Point(y, x * -1);
    }

    public int manhattanDistance(Point point) {
        return Math.abs(point.x - x) + Math.abs(point.y - y);
    }
}
