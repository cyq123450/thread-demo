package com.cyq.compare.comparable.demo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comparable比较器
 */
public class ComparableDemo01 {

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog(1, "AA"));
        dogs.add(new Dog(5, "BB"));
        dogs.add(new Dog(3, "CC"));
        dogs.add(new Dog(10, "RR"));
        dogs.add(new Dog(7, "TT"));

        System.out.println("排序前");
        for(Dog dog : dogs) {
            System.out.println(dog);
        }

        Collections.sort(dogs);

        System.out.println("排序后");
        for(Dog dog : dogs) {
            System.out.println(dog);
        }

    }

}
