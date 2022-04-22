package modern_java_in_action.streamAPI.sequential_streams;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataStream {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        // Data stream from array
        int[] numbers = {2, 3, 5, 7, 11, 13};

        int sum = Arrays.stream(numbers).sum();

        System.out.println("Sum: " + sum);

        // Data stream from file (Yeah, sh*t code, I know)
        long uniqueWords = 0;
        URL urlRes = Optional
                .ofNullable(DataStream.class.getClassLoader().getResource("data.txt"))
                .orElse(new URL("file:/D:/JavaProjects/StreamAPI/target/classes/data.txt"));
        URI uriRes = urlRes.toURI();

        try (Stream<String> lines = Files.lines(Paths.get(uriRes), Charset.defaultCharset())) {

            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Unique words: " + uniqueWords);

        // Generated data streams (Fib pairs)
        Stream.iterate(new int[]{0, 1}, ints -> new int[]{ints[1], ints[0] + ints[1]})
                .limit(20)
                //.map(ints -> ints[0])
                //.forEach(System.out::println);
                .forEach(ints -> System.out.println(Arrays.toString(ints)));

        // Stream of random numbers with generate method
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // Generate fib numbers with IntSupplier interface
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;

                this.previous = this.current;
                this.current = nextValue;

                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
