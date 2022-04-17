package modern_java_in_action.streamAPI.dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitionStream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        partitionedMenu(menu);
        mostCaloricPartitionedByVegetarian(menu);

        Map<Boolean, List<Integer>> map = partitionPrimes(10);
        for (Boolean bool : map.keySet()) {
            System.out.println(bool + " = " + map.get(bool));
        }
    }

    private static void partitionedMenu(List<Dish> menu) {
        Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedMenu =
                menu.stream()
                        .collect(Collectors.partitioningBy(
                                        Dish::isVegetarian,
                                        Collectors.groupingBy(Dish::getType)
                                )
                        );

        for (Boolean bool : partitionedMenu.keySet()) {
            System.out.println(bool + " = " + partitionedMenu.get(bool));
        }
    }

    private static void mostCaloricPartitionedByVegetarian(List<Dish> menu) {
        Map<Boolean, Dish> result = menu.stream()
            .collect(
                Collectors.partitioningBy(
                    Dish::isVegetarian,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                    )
                )
            );

        for (Boolean bool : result.keySet()) {
            System.out.println(bool + " = " + result.get(bool));
        }

    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        Predicate<Integer> isPrime = candidate -> {
            int candidateRoot = (int) Math.sqrt((double) candidate);

            return IntStream.rangeClosed(2, candidateRoot)
                    .noneMatch(i -> candidate % i == 0);
        };

        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(
                        Collectors.partitioningBy(isPrime)
                );
    }
}
