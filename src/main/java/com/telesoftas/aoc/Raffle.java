package com.telesoftas.aoc;

import java.security.SecureRandom;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.nCopies;

public class Raffle {
    public static void main(String[] args) {
        var winners = asList(
                // TOP participant
                "Kennedy Ibeh\t12",
                // Lucky ones
                "Danielius Strašunskis\t7",
                "Karolis Šlipaitis\t11",
                "Giedrius Budrys\t4"
        );

        String tsv = "Ana Paidem\t6\n" +
                "Edvinas Urvinis\t6\n" +
                "Ivan Kisanov\t4\n" +
                "John Ojetunde\t10\n" +
                "Justas Subatavičius\t7\n" +
                "Justas Sperauskas\t5";

        SecureRandom secureRandom = new SecureRandom();

        stream(tsv.split("\n"))
                .map(line -> line.split("\t"))
                .flatMap(values -> nCopies(parseInt(values[1]), values[0]).stream())
                .sorted((o1, o2) -> secureRandom.nextInt())
//                .forEach(System.out::println);
                .findAny()
                .ifPresent(System.out::println);
    }
}


