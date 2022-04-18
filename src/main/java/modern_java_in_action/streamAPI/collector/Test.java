package modern_java_in_action.streamAPI.collector;

import modern_java_in_action.streamAPI.dish.Dish;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Dish> menu = new Dish().initializeMenu();

        List<Dish> dishes = menu.stream().collect(new ToListCollector<>());

        System.out.println(dishes);
    }
}
