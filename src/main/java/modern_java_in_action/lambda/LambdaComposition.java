package modern_java_in_action.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaComposition {
    private static final List<Apple> inventory = new ArrayList<>();

    static {
        inventory.add(new Apple(12, Color.RED));
        inventory.add(new Apple(12, Color.YELLOW));
        inventory.add(new Apple(10, Color.GREEN));
        inventory.add(new Apple(14, Color.RED));
        inventory.add(new Apple(10, Color.YELLOW));
    }

    public static void main(String[] args) {
        inventory.sort(Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        System.out.println(inventory);

        Predicate<Apple> redApple = a -> a.getColor().equals(Color.RED);

        System.out.println(redApple.negate().test(new Apple(12, Color.RED)));
        System.out.println(redApple.negate().test(new Apple(12, Color.YELLOW)));

        List<Apple> filteredApples = new ArrayList<>();
        Predicate<Apple> appleWeightMoreThen10 = apple -> {
            return apple.getWeight() > 10;
        };

        Predicate<Apple> appleColorIsYellow = apple -> {
            return apple.getColor().equals(Color.YELLOW);
        };

        Predicate<Apple> complexPredicate =
                appleWeightMoreThen10.and(appleColorIsYellow);

        for (Apple apple : inventory) {
            if (complexPredicate.test(apple)) {
                filteredApples.add(apple);
            }
        }

        System.out.println(filteredApples);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);

        System.out.println(h1.apply(2));
        System.out.println(h2.apply(2));
    }
}
