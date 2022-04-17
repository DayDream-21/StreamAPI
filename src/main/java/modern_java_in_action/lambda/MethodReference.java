package modern_java_in_action.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    public static void main(String[] args) {
        Supplier<String> supplier = String::new;
        String str = supplier.get();
        System.out.println(str.isEmpty());

        Function<Integer, Integer> function = Integer::intValue;
        Integer integer = function.apply(10);
        System.out.println(integer);

        BiFunction<Integer, Color, Apple> createApple = Apple::new;
        Apple apple1 = createApple.apply(10, Color.YELLOW);
        Apple apple2 = createApple.apply(15, Color.GREEN);
        System.out.println(apple1);
        System.out.println(apple2);

        TriFunction<String, Integer, Color, Fruit> createFruit = Fruit::new;
        Fruit fruit1 = createFruit.apply("Orange", 14, Color.ORANGE);
        System.out.println(fruit1);

    }
}
