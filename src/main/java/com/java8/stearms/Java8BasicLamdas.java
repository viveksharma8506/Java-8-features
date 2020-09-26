package com.java8.stearms;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Java8BasicLamdas {


    public static void main(String [] args){

        String [] s = {"green","red","blue"};

        String wel ="welcometojava";

        edges(wel,3);


       /* List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),

                new Dish("beef", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

       List<String> filteredDish = menu.stream()
               .filter(dish -> dish.getCalories()>500 )
               .map(Dish::getName)
               .limit(3)
               .sorted(String::compareTo)
               .collect(Collectors.toList());
        //filteredDish.sort(Comparator.comparing(String::toString).reversed().)
        System.out.println(filteredDish);*/


       /* Java 8 sort without stream*/
        /* menu.sort(Comparator.comparing(Dish :: getName)
                .reversed()
                .thenComparing(Dish::getCalories));
        System.out.println(menu);*/









    }



    private static void  edges(String line, int k){
        String smallest = "";
        String largest ="";

        TreeSet<String> tree = new TreeSet<>();
        int len = line.length();
        for(int i=0 ; i<=len-k ; i++){
            tree.add(line.substring(i,i+k));

        }
        smallest = tree.first();
        largest= tree.last();
        System.out.println(tree.pollLast());
        System.out.println(smallest+ "\n" +largest);
    }

    public List<Apple> getWeightedApples(List<Apple> apples,ApplePredicate<Integer> predicate){

    return null;}


}

@FunctionalInterface
 interface ApplePredicate<T>{
    public boolean test(T t);


}
