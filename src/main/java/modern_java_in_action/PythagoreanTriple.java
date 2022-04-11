package modern_java_in_action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PythagoreanTriple {
    public static void main(String[] args) {
        int limit = 5;
        System.out.print(limit + " Pythagorean triples: ");
        for(double[] triples : getPythagoreanTriples(limit)) {
            System.out.print(Arrays.toString(triples) + " ");
        }
    }

    private static List<double[]> getPythagoreanTriples(int limit) {
       return IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b ->
                                        new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(triples -> triples[2] % 1 == 0)
                        )
               .limit(limit)
               .collect(Collectors.toList());
    }
}
