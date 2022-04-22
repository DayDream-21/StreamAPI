package modern_java_in_action.streamAPI.sequential_streams;

public class CollectorsHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            PartitionStream.partitionPrimesWithCustomCollector(1_000_000);
            //PartitionStream.partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }

        System.out.println("Fastest time completion: " + fastest + " millis");
    }
}
