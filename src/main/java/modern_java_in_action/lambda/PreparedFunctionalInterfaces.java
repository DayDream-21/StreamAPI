package modern_java_in_action.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class PreparedFunctionalInterfaces {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 3, 6, 2, 4, 7, 9, 8);

        Predicate<Integer> evenNumberPredicate = i -> i % 2 == 0;
        IntPredicate oddNumberPredicate = i -> i % 2 != 0;
        IntPredicate moreThen100Predicate = i -> i > 100;

        System.out.println(filter(ints, evenNumberPredicate));
        System.out.println(oddNumberPredicate.test(1000) + " "
                + oddNumberPredicate.test(13)); // without autoboxing

        System.out.println(oddNumberPredicate.and(moreThen100Predicate).test(113));
        System.out.println(oddNumberPredicate.or(moreThen100Predicate).test(1000));

        Consumer<Integer> printAllConsumer = System.out::println;
        forEach(ints, printAllConsumer);

        List<String> strings = Arrays.asList("One", "Two", "Three", "Four");
        Function<String, Integer> strToIntFunction = String::length;
        System.out.println(map(strings, strToIntFunction));
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }

        return results;
    }

    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(function.apply(t));
        }

        return result;
    }
}
