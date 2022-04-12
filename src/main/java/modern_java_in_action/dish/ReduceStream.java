package modern_java_in_action.dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceStream {
    public static void main(String[] args) throws NoSuchMethodException {
        // Special menu is already sorted by calories
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true,  120, Dish.Type.OTHER),
                new Dish("prawns",         false, 300, Dish.Type.FISH),
                new Dish("rice",           true,  350, Dish.Type.OTHER),
                new Dish("chicken",        false, 400, Dish.Type.MEAT),
                new Dish("french fries",   true,  530, Dish.Type.OTHER),
                new Dish("beef",           false, 400, Dish.Type.MEAT)
        );

        System.out.println("Dishes with calories less than 320: " +
                getDishesCaloriesLessThan(specialMenu, 320));

        System.out.println("Dishes with calories more than 320: " +
                getDishesCaloriesMoreThan(specialMenu, 320));

        System.out.println("3 dishes with calories more than 290: " +
                getDishesWithLimit(specialMenu));

        System.out.println("Filtered and skipped dishes: " +
                getDishesWithFilterAndSkip(specialMenu));

        System.out.println("First two meat dishes: " +
                getFirstTwoMeatDishes(specialMenu));
    }

    /**
     * Returns list of dishes with calories less than method parameter calories
     *
     * @apiNote It's better to use this method on a sorted array
     * @param menu list of dishes
     * @param calories upper bound of calories inclusively
     * @return list of suitable dishes
     */
    private static List<Dish> getDishesCaloriesLessThan(List<Dish> menu, int calories) {
        return menu.stream()
                .takeWhile(dish -> dish.getCalories() <= calories)
                .collect(Collectors.toList());
    }

    /**
     * Returns list of dishes with calories more than method parameter calories
     *
     * @apiNote it's better to use this method on a sorted array
     * @param menu list of dishes
     * @param calories bottom bound of calories inclusively
     * @return list of suitable dishes
     */
    private static List<Dish> getDishesCaloriesMoreThan(List<Dish> menu, int calories) {
        return menu.stream()
                .dropWhile(dish -> dish.getCalories() <= calories)
                .collect(Collectors.toList());
    }

    private static List<Dish> getDishesWithLimit(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 290)
                .limit(3)
                .collect(Collectors.toList());
    }

    private static List<Dish> getDishesWithFilterAndSkip(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
    }

    private static List<Dish> getFirstTwoMeatDishes(List<Dish> menu) {
        return menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .collect(Collectors.toList());
    }
}
