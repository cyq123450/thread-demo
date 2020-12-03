package com.cyq.lambda.demo02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaDemo01 {

    public static void main(String[] args) {
        Person[] peos = {new Person(1, "Jack"),
                new Person(5, "king"),
                new Person(2, "Bob"),
                new Person(9, "Tom"),
                new Person(4, "Tim")};

        // 1.forEach使用
        List<Person> list1 = Arrays.asList(peos);
        System.out.println("1.");
        list1.forEach(person -> System.out.println(person));

        // 1-1.forEach使用
        System.out.println("1-1");
        Consumer<Person> consumer1 = e -> e.setId(e.getId() + e.getId() * 2);
        list1.forEach(consumer1);
        list1.forEach(p -> System.out.println(p));

        // 2.filter使用
        System.out.println("2.");
        List<Person> list2 = Arrays.asList(peos);
        list2.stream()
                .filter((p) -> (p.getId() > 6))
                .forEach(p -> System.out.println(p));

        // 3.自定义过滤器
        System.out.println("3.");
        List<Person> list3 = Arrays.asList(peos);
        Predicate<Person> pId = (p) -> p.getId() > 6;
        list3.stream()
                .filter(pId)
                .forEach(p -> System.out.println(p));

        // 4.limit使用
        System.out.println("4.");
        List<Person> list4 = Arrays.asList(peos);
        list4.stream()
                .filter((p) -> (p.getId() > 6))
                .limit(1)
                .forEach(p -> System.out.println(p));

        // 5.sorted使用
        System.out.println("5.");
        List<Person> list5 = Arrays.asList(peos);
        List<Person> collect1 = list5.stream()
                .sorted(((p, p1) -> (p.getId().compareTo(p1.getId()))))
                .limit(3)
                .collect(Collectors.toList());      // 把结果集返回成一个集合
        collect1.forEach(p -> System.out.println(p));

        // 5.max使用
        System.out.println("6.");
        List<Person> list6 = Arrays.asList(peos);
        Person person = list6.stream()
                .max((p, p1) -> (p.getId().compareTo(6)))
                .get();
        System.out.println(person);

        // 6.min使用
        System.out.println("7.");
        List<Person> list7 = Arrays.asList(peos);
        Person person1 = list7.stream()
                .min((p, p1) -> (p.getId()))
                .get();
        System.out.println(person1);


    }

}
