package com.telesoftas.adventofcode.day15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Wall {

    private final String name;
    private final Map<Wall, Integer> adjacentNodes = new HashMap<>();
    private List<Wall> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;

    public Wall(String name) {
        this.name = name;
    }

    public void addDestination(Wall destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Map<Wall, Integer> getAdjacentWalls() {
        return adjacentNodes;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Wall> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Wall> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getName() {
        return name;
    }
}
