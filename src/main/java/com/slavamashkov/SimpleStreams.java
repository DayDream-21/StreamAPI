package com.slavamashkov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleStreams {
    public static void main(String[] args) {
        Function<Integer, Double> square = i -> Math.pow(i, 2.0);

        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 6, 7, 8));

        List<Double> result = ints.stream()
                .filter(integer -> integer % 2 == 0)
                .map(square)
                .distinct()
                .limit(4)
                .collect(Collectors.toList());

        System.out.println(result);

        ints.stream().filter(integer -> integer % 2 == 1).forEach(System.out::println);

        Stream.of("SSS", "AA", "BBBB").map(String::length).forEach(System.out::println);
    }
}
