package com.java8.excercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TradeDemo {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");

        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high).

        List<Transaction> sorted = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

       // sorted.stream().forEach(transaction -> System.out.println(transaction.getValue()));

        // 2. What are all the unique cities where the traders work?
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

      //  collect.stream().forEach(System.out::println);

        //  3. Find all traders from Cambridge and sort them by name.

        List<Trader> cambridge = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("cambridge"))
                                .map(Transaction::getTrader)
                                .distinct()
                                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        //cambridge.stream().forEach(trader -> System.out.println(trader.getName()));

        // 4. Return a string of all traders’ names sorted alphabetically.
        List<Trader> traderList = transactions.stream().map(Transaction::getTrader)
                .distinct()
                .sorted((t, r) -> t.getName().compareTo(r.getName()))
                .collect(Collectors.toList());

       // traderList.stream().forEach(trader -> System.out.println(trader.getName()) );

        // 5. Are any traders based in Milan?
        boolean milan = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("Milan"))
                .findAny()
                .isPresent();


        System.out.println(milan);

        // 6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge"))
               .forEach(transaction -> System.out.println(transaction.getValue()));

        // 7. What’s the highest value of all the transactions?
        transactions.stream().max(Comparator.comparing(Transaction::getValue))
        .ifPresent(System.out::println);

        //OR
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).ifPresent(System.out::println);
        //8. Find the transaction with the smallest value.

        transactions.stream().min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }
}
