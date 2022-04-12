package modern_java_in_action.dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MatchStream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        System.out.println("Is menu partially vegetarian? " +
                isMenuPartiallyVegetarian(menu));
        System.out.println("Is menu healthy (all dishes has less then 1000 cal.)? " +
                isMenuHealthy(menu));

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(n -> n * n)
                        .filter(n -> n % 3 == 0)
                        .findFirst(); // Результат равен 9
    }

    private static boolean isMenuPartiallyVegetarian(List<Dish> menu) {
        return menu.stream()
                .anyMatch(Dish::isVegetarian);
    }

    private static boolean isMenuHealthy(List<Dish> menu) {
        return menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
    }
}
