package modern_java_in_action.streamAPI.sequential_streams.collector;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class PrimeNumbersCollector implements Collector<Integer,
                                                        Map<Boolean, List<Integer>>,
                                                        Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    private static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return IntStream.rangeClosed(2, candidateRoot)
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate))
                    .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));

            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
