package modern_java_in_action.lambda;

import lombok.Data;
import lombok.NonNull;

@Data
class Fruit {
    @NonNull
    private String name;

    @NonNull
    private Integer weight;

    @NonNull
    private Color color;
}
