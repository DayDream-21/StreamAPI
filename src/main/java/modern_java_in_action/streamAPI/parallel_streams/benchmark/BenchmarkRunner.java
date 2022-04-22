package modern_java_in_action.streamAPI.parallel_streams.benchmark;

/**
 * <h3>Benchmark results</h3>
 * <pre>
 * Benchmark             Mode  Cnt   Score   Error  Units
 * ...iterativeSum       avgt    5   3,865 ± 0,124  ms/op
 * ...parallelRangedSum  avgt    5   1,165 ± 0,193  ms/op
 * ...parallelSum        avgt    5  79,343 ± 2,273  ms/op
 * ...rangedSum          avgt    5   4,184 ± 0,129  ms/op
 * ...sequentialSum      avgt    5  82,955 ± 5,210  ms/op
 * </pre>
 */

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception{
        org.openjdk.jmh.Main.main(args);
    }
}
