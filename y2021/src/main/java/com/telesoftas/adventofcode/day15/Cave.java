package com.telesoftas.adventofcode.day15;

import java.util.HashSet;
import java.util.Set;

public class Cave {

    private Set<Wall> walls = new HashSet<>();
    private Wall source;

    public void addWall(Wall wall) {
        if (walls.isEmpty()) {
            source = wall;
        }
        walls.add(wall);
    }


    public Wall getSource() {
        return source;
    }

    public Wall getWall(String name) {
        return walls.stream().filter(wall -> wall.getName().equals(name)).findFirst().orElseThrow();
    }
}
