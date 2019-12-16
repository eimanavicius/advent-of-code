package com.telesoftas.aoc;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Orbits {

    public Map<String, String> parseToMap(String input) {
        return Arrays.stream(input.split("\n"))
            .collect(toMap(this::getKey, this::getValue));
    }

    <U, T> String getKey(String t) {
        return t.substring(t.indexOf(")") + 1);
    }

    <K, T> String getValue(String t) {
        return t.substring(0, t.indexOf(")"));
    }

    public int calculate(Map<String, String> orbits) {
        return orbits.keySet().stream()
            .mapToInt(name -> getParents(name, orbits, 0))
            .sum();
    }

    private int getParents(String name, Map<String, String> orbits, int i) {
        if (!orbits.containsKey(name)) {
            return i;
        } else {
            return getParents(orbits.get(name), orbits, ++i);
        }
    }

    public Set<String> extractPathToCOM(final Map<String, String> map, String child) {
        HashSet<String> parents = new HashSet<>();

//        // beak condition
//        if (!"COM".equals(child)) {
//            // get parent of the child
//            String parent = map.get(child);
//            parents.add(parent);
//            // recursion
//            parents.addAll(extractPathToCOM(map, parent));
//        }

        // loop
        while (!"COM".equals(child)) {
            String parent = map.get(child);
            parents.add(parent);
            child = parent;
        }

        return parents;
    }

    public int findPathDiff(final Map<String, String> map, final String you, final String san) {
        Set<String> pathToYOU = extractPathToCOM(map, you);
        Set<String> pathToSAN = extractPathToCOM(map, san);


        /* we work with this graph further on
         *
         *                           YOU
         *                          /
         *                     J - K
         *                    /
         * COM - B - C - D - E
         *                \
         *                 I - SAN
         */

        // find a collection of diff nodes YOU to SAN
        Set<String> diffYOU = new HashSet<>(pathToYOU);
        diffYOU.removeAll(pathToSAN);

        // find a collection of diff nodes SAN to YOU
        Set<String> diffSAN = new HashSet<>(pathToSAN);
        diffSAN.removeAll(pathToYOU);

        // combine resulting collections sizes
        return diffSAN.size() + diffYOU.size();
    }
}
