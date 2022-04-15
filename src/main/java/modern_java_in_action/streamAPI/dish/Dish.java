package modern_java_in_action.streamAPI.dish;

import java.util.Arrays;
import java.util.List;

public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish() {
    }

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public List<Dish> initializeMenu() {
        return Arrays.asList(
                new Dish("pork",         false, 800, Dish.Type.MEAT),
                new Dish("beef",         false, 700, Dish.Type.MEAT),
                new Dish("chicken",      false, 400, Dish.Type.MEAT),
                new Dish("french fries", true,  530, Dish.Type.OTHER),
                new Dish("rice",         true,  350, Dish.Type.OTHER),
                new Dish("season fruit", true,  120, Dish.Type.OTHER),
                new Dish("pizza",        true,  550, Dish.Type.OTHER),
                new Dish("prawns",       false, 300, Dish.Type.FISH),
                new Dish("salmon",       false, 450, Dish.Type.FISH)
        );
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
