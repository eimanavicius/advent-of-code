package com.telesoftas.adventofcode.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Chiton {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day15.txt")) {
            List<List<Integer>> riskLevels = toRiskLevels(input);
            Cave cave = toCave(riskLevels);

            String target = (riskLevels.size() - 1) + "," + (riskLevels.get(0).size() - 1);
            int lowestTotalRisk = lowestTotalRisk(cave, target);

            System.out.println("What is the lowest total risk of any path from the top left to the bottom right?");
            System.out.println(lowestTotalRisk);
        }
    }

    public static Cave toCave(List<List<Integer>> riskLevels) {
        int height = riskLevels.size();
        int width = riskLevels.get(0).size();
        Wall[][] walls = new Wall[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                walls[i][j] = new Wall(i + "," + j);
            }
        }

        Cave cave = new Cave();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Wall wall = walls[i][j];
                if (i + 1 < height) {
                    wall.addDestination(walls[i + 1][j], riskLevels.get(i + 1).get(j));
                }
                if (i - 1 >= 0) {
                    wall.addDestination(walls[i - 1][j], riskLevels.get(i - 1).get(j));
                }
                if (j + 1 < width) {
                    wall.addDestination(walls[i][j + 1], riskLevels.get(i).get(j + 1));
                }
                if (j - 1 >= 0) {
                    wall.addDestination(walls[i][j - 1], riskLevels.get(i).get(j - 1));
                }
                cave.addWall(wall);
            }
        }
        return cave;
    }

    public static int lowestTotalRisk(Cave riskyCave, String target) {
        calculateShortestPathFromSource(riskyCave, riskyCave.getSource());

        Wall destination = riskyCave.getWall(target);

        return destination.getDistance();
    }

    public static Cave calculateShortestPathFromSource(Cave graph, Wall source) {
        source.setDistance(0);

        Set<Wall> settledWalls = new HashSet<>();
        Set<Wall> unsettledWalls = new HashSet<>();

        unsettledWalls.add(source);

        while (unsettledWalls.size() != 0) {
            Wall currentWall = getLowestDistanceWall(unsettledWalls);
            unsettledWalls.remove(currentWall);
            for (Entry<Wall, Integer> adjacencyPair : currentWall.getAdjacentWalls().entrySet()) {
                Wall adjacentWall = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledWalls.contains(adjacentWall)) {
                    calculateMinimumDistance(adjacentWall, edgeWeight, currentWall);
                    unsettledWalls.add(adjacentWall);
                }
            }
            settledWalls.add(currentWall);
        }
        return graph;
    }

    private static Wall getLowestDistanceWall(Set<Wall> unsettledWalls) {
        Wall lowestDistanceWall = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Wall node : unsettledWalls) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceWall = node;
            }
        }
        return lowestDistanceWall;
    }

    private static void calculateMinimumDistance(Wall evaluationNode, Integer edgeWeigh, Wall sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Wall> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static List<List<Integer>> toRiskLevels(InputStream input) {
        return new BufferedReader(new InputStreamReader(input))
            .lines()
            .map(line -> line.chars().mapToObj(x -> x - '0').toList())
            .toList();
    }
}