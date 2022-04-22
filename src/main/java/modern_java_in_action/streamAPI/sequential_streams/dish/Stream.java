package modern_java_in_action.streamAPI.sequential_streams.dish;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        System.out.println("Low caloric dishes name: " +
                getLowCaloricDishesName(menu));

        System.out.println("Low caloric dishes name (stream) : " +
                getLowCaloricDishesNameStream(menu));

        System.out.println("Low caloric dishes name (parallel stream): " +
                getLowCaloricDishesNameParallelStream(menu));

        System.out.println("Grouped dishes: " +
                getDishesByType(menu));

        System.out.println("Three high caloric dish names: " +
                getThreeHighCaloricDishesName(menu));
    }

    // Code to get names of low caloric dishes (calories < 400) before Java 8
    private static List<String> getLowCaloricDishesName(List<Dish> menu) {
        List<Dish> lowCaloricDishes = new ArrayList<>();

        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();

        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
        return lowCaloricDishesName;
    }

    // Code to get names of low caloric dishes (calories < 400) after Java 8
    private static List<String> getLowCaloricDishesNameStream(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    private static List<String> getLowCaloricDishesNameParallelStream(List<Dish> menu) {
        return menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    // Grouping all dishes by its type
    private static Map<Dish.Type, List<Dish>> getDishesByType(List<Dish> menu) {
        return menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
    }

    private static List<String> getThreeHighCaloricDishesName(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 400)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Dish> getVegetarianDishesName(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
    }
}
