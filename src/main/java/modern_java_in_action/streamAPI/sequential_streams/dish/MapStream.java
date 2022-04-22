package modern_java_in_action.streamAPI.sequential_streams.dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MapStream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        System.out.println("Dishes name: "
                + getDishesName(menu));

        System.out.println("Dishes ordered by name length: "
                + getDishesOrderedByNamesLength(menu));

        List<Integer> ints = List.of(1, 2, 3, 4, 5);
        List<Integer> squares = ints.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());

        System.out.println("Squares: " + squares);

        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(3, 4);

        List<int[]> pairs = first.stream()
                .flatMap(i -> second.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[] {i, j}))
                .collect(Collectors.toList());

        pairs.forEach(p -> System.out.println(Arrays.toString(p)));
    }

    private static List<String> getDishesName(List<Dish> menu) {
        return menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    private static List<String> getDishesOrderedByNamesLength(List<Dish> menu) {
        return menu.stream()
                .map(Dish::getName)
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
    }
}
