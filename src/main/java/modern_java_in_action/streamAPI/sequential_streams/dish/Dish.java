package modern_java_in_action.streamAPI.sequential_streams.dish;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"vegetarian", "calories", "type"})
public class Dish {
    @NonNull
    private String name;

    @NonNull
    private boolean vegetarian;

    @NonNull
    private int calories;

    @NonNull
    private Type type;

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

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
