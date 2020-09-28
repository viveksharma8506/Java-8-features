package com.java8.stearms;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    }

}
