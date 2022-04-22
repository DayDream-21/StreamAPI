package modern_java_in_action.streamAPI.parallel_streams;

import java.util.stream.LongStream;

public class SideEffect {
    static class Accumulator {
        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }

    public static void main(String[] args) {
        System.out.println(sideEffectSum(100));
        System.out.println(sideEffectParallelSum(100));
    }

    private static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.total;
    }

    private static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }
}
