package modern_java_in_action.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LambdaTest {
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static void main(String[] args) throws IOException {
        BufferedReaderProcessor getOneLine = br -> br.readLine();
        BufferedReaderProcessor getTwoLines = br -> br.readLine() + br.readLine();

        System.out.println("One line: " + processFile(getOneLine));
        System.out.println("Two lines: " + processFile(getTwoLines));
    }

    private static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("src/main/resources/data.txt"))) {
            return processor.process(br);
        }
    }
}
