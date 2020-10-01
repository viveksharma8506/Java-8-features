package com.java8.stearms;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsFeatures {

    public static void main(String [] args){



        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),

                new Dish("beef", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        //filter method

        List<Dish> vegDishes =menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());


        //filter unique values--give list of dish with unique name {removing duplicate beef}where cal >500
        List<String> cal500Dish =menu.stream().filter(dish -> dish.getCalories()>500)
                .map(Dish::getName)
                .distinct()
                .collect(Collectors.toList());


        //Limit fix the size of stream
        List<String> filteredDish = menu.stream()
                .filter(dish -> dish.getCalories()>500 )
                .map(Dish::getName)
                .limit(3)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        //filteredDish.sort(Comparator.comparing(String::toString).reversed().)
        System.out.println(filteredDish);


        //Streams support the skip(n) method to return a stream that discards the first n elements. If the
        //stream has fewer elements than n, then an empty stream is returned
        // skip first 2 meat dishes
       List<Dish> afterSkipFirst2MeatDish= menu.stream()
                .filter(dish ->
                    dish.getType()== Dish.Type.MEAT)
                .skip(2)
                .collect(Collectors.toList());


        //How would you use streams to filter the first two meat dishes?

        List<Dish> first2MeatDish= menu.stream()
                .filter(dish ->
                        dish.getType()== Dish.Type.MEAT)
               .limit(2)
                .collect(Collectors.toList());


        //MAP-Given a list of
        //words, you’d like to return a list of the number of characters for each word. How would you do it?
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> lngthList = words.stream()
                .map(String::length)
                .collect(Collectors.toList());


        //FLAT MAP -how could you return a list of all the unique characters for a list of words? For
        // example, given the list of words ["Hello", "World"] you’d like to return the list ["H", "e", "l", "o",
        //       "W", "r", "d"].
        List<String> wordList = Arrays.asList("hello", "world");
        wordList.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream )
                .distinct()
                .collect(Collectors.toList());

       // Ex-mapping
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> Squares = numbers.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());

        //Given two lists of numbers, how would you return all pairs of numbers? For example, given a
        //list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> array = numbers1.stream()
                .flatMap(integer -> numbers2.stream()
                        .map(integer1 -> new int[]{integer, integer1}))
                .collect(Collectors.toList());

      array.stream().
              forEach(ints -> Arrays.stream(ints).forEach(System.out::println));

        // pair divisible by 3
        List<int[]> collect = numbers1.stream()
                .flatMap(integer -> numbers2.stream()
                        .filter(i -> i + integer % 3 == 0)
                        .map(integer1 -> new int[]{integer, integer1}))
                .collect(Collectors.toList());


        // find , find any , all match  none match




        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny() // return optional here
                .ifPresent(System.out::println); // evaluation on optional values


        //find first
        //the code that follows, given a list of numbers,
        //finds the first square that’s divisible by 3:

        List<Integer> intList= Arrays.asList(1,2,3,4,5,6,7);
        Optional<Integer> first = intList.stream()
                .map(i -> i *i )
                .filter(x -> x % 3 == 0)
                .findFirst();
        
        
        //Reduce Operation folds :
        
        Integer reduce = intList.stream().reduce(0, (a, b) -> a + b);
        //OR
        Integer sum =  intList.stream().reduce(0,Integer::sum);
        
        //Max
        Optional<Integer> reduce1 = intList.stream().reduce(Integer::max);

        //You could have equally well used the lambda (x,y)->x<y?x:y instead of Integer::min, but the
        //latter is easier to read.
        Optional<Integer> reduce2 = intList.stream().reduce(Integer::min);



        //How would you count the number of dishes in a stream using the map and reduce methods?

        menu.stream().map(dish -> 1).reduce(0,Integer::sum);


       // Java 8 introduces three primitive specialized stream interfaces to tackle this issue,
        // IntStream, DoubleStream, and LongStream, that respectively specialize the elements of a stream to be int, long, and double—and
        // thereby avoid hidden boxing costs.

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        // Numeric Streams and there specilaized optinals
        //The sum example was convenient because it has a default value: 0. But if you want to calculate
        //the maximum element in an IntStream, you need something different because 0 is a wrong
        //result. How can you differentiate that the stream has no element and that the real maximum is 0?
        //Earlier we introduced the Optional class, which is a container that indicates the presence or
        //absence of a value. Optional can be parameterized with reference types such as Integer, String, and so on. There’s a primitive specialized version of Optional as well for the three primitive
        //stream specializations: OptionalInt, OptionalDouble, and OptionalLong.

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();



        //Java 8 introduces two
        //static methods available on IntStream and LongStream to help generate such ranges: range and
        ///rangeClosed. Both methods take the starting value of the range as the first parameter and the
       // end value of the range as the second parameter. But range is exclusive, whereas rangeClosed is
        //inclusive. Let’s look at an example:

        long count = IntStream.rangeClosed(1, 100)
                .filter(integer -> integer % 2 == 0)
                .count();

        //if you were using
        //IntStream.range(1, 100) instead, the result would be 49 even numbers because range is
        //exclusive.

        //t the famous Greek mathematician Pythagoras discovered
        //that certain triples of numbers (a, b, c) satisfy the formula a * a + b * b = c * c where a, b, and c
        //are integers. For example, (3, 4, 5) is a valid Pythagorean triple because 3 * 3 + 4 * 4 = 5 * 5 or 9
        //+ 16 = 25. There are an infinite number of such triples
        //generate all such triple in range 1 to 100

        IntStream.rangeClosed(1,100).boxed()
                .flatMap(a->IntStream.rangeClosed(1,100)
                .mapToObj(b->new double [] {a,b, Math.sqrt(a*a+b*b)}))
                .filter(t->t[2]%1==0)
                .forEach(t-> Arrays.stream(t).forEach(System.out::println)); //Streams from arrays

        //TO Do - get result in expected format



       //. Streams from values

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

//Iterate
//Let’s look at a simple example of how to use iterate before we explain it:
        Stream.iterate(0, n -> n + 2)
                .limit(10)

                .forEach(System.out::println);


        //Iterate
       // Let’s look at a simple example of how to use iterate before we explain it:

        // Fibonacci series
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);


        // Fibonacci series -pairs
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));


        // // Stream genarate - to generate infinite Stream -Must provide Limit

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }


}
