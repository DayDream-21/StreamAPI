package modern_java_in_action.dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        long howManyDishes = menu.stream().collect(Collectors.counting());

        Optional<Dish> mostCaloriesDish = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));

        int totalCalories = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));

        double avgCalories = menu.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));

        IntSummaryStatistics menuStatistic = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(menuStatistic);

        String shortMenu = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));

        System.out.println(shortMenu);

        int totalCaloriesReducing = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }
}
