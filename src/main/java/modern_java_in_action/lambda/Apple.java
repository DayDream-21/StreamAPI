package modern_java_in_action.lambda;

import lombok.Data;
import lombok.NonNull;

@Data
class Apple {
    @NonNull
    private Integer weight;

    @NonNull
    private Color color;
}
