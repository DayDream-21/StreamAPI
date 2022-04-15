package modern_java_in_action.dish;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStream {
    public enum CaloriesLevel {
        DIET,
        NORMAL,
        FAT
    }

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

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        System.out.println(dishesByType);

        Map<CaloriesLevel, List<Dish>> dishesByCalories = menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloriesLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloriesLevel.NORMAL;
                    } else {
                        return CaloriesLevel.FAT;
                    }
                }));

        System.out.println(dishesByCalories);

        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));

        System.out.println(caloricDishesByType);

        Map<Dish.Type, Map<CaloriesLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloriesLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloriesLevel.NORMAL;
                                    } else {
                                        return CaloriesLevel.FAT;
                                    }
                                }))
                );

        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}
