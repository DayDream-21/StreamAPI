package modern_java_in_action.transactions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TraderTransactionTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader bobby = new Trader("Bobby", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(bobby, 2012, 950)
        );

        System.out.println("1) " + solution1(transactions));
        System.out.println("2) " + solution2(transactions));
        System.out.println("3) " + solution3(transactions));
        System.out.println("4) " + solution4(transactions));
        System.out.println("5) " + solution5(transactions));
        System.out.println("6) " + solution6(transactions));
        System.out.println("7) " + solution7(transactions));
        System.out.println("8) " + solution8(transactions));
    }

    /**
     * <p>Find all transactions for 2011 and sort them by value (lowest to highest).</p>
     */
    private static List<Transaction> solution1(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    /**
     * <p>Display a list of non-repeating cities where traders work.</p>
     */
    private static List<String> solution2(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * <p>Find all traders from Cambridge and sort them by name.</p>
     */
    private static List<Trader> solution3(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    /**
     * <p>Return a string with all trader names sorted alphabetically.</p>
     */
    private static String solution4(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .reduce("", (name1, name2) -> name1 + " " + name2)
                .trim();
    }

    /**
     * <p>Find out if there is at least one trader from Milan.</p>
     */
    private static boolean solution5(List<Transaction> transactions) {
        return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Milan"));
    }

    /**
     * <p>Display the sums of all transactions of traders from Cambridge.</p>
     */
    private static List<Integer> solution6(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
    }

    /**
     * <p>What is the maximum value among all transactions?</p>
     */
    private static Integer solution7(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
    }

    /**
     * <p>Find the transaction with the minimum value.</p>
     */
    private static Transaction solution8(List<Transaction> transactions) {
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .orElse(null);
    }
}
