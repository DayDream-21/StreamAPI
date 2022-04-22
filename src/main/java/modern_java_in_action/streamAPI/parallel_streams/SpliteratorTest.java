package modern_java_in_action.streamAPI.parallel_streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpliteratorTest {
    public static void main(String[] args) {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";

        System.out.println("Find " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Find " + countWordsStream(SENTENCE) + " words");
    }

    private static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;

        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }

        return counter;
    }

    private static int countWordsStream(String s) {
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(s::charAt);

        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);

        return wordCounter.getCounter();
    }
}
