package modern_java_in_action.streamAPI.parallel_streams.benchmark;

import modern_java_in_action.streamAPI.parallel_streams.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class CustomParallelCalculatorTest {
    public static void main(String[] args) {
        System.out.println(forkJoinSum(1_000_000));
    }

    private static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
