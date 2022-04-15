package modern_java_in_action.streamAPI.dish;

import java.util.List;

public class FoldStream {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        System.out.println("Avg calories per dish: " +
                avgCaloriesPerDish(menu));

        System.out.println("Dish with max calories: " +
                maxCalories(menu));

        System.out.println("Amount of dishes: " +
                amountOfDishes(menu));
    }

    private static int avgCaloriesPerDish(List<Dish> menu) {
        int totalCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (a, b) -> a + b);

        return totalCalories / menu.size();
    }

    private static int maxCalories(List<Dish> menu) {
         return menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::max);
    }

    private static int amountOfDishes(List<Dish> menu) {
        return menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
    }
}
