package com.cyq.lambda.demo02;

import java.util.*;
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


        peos = new Person[]{new Person(1, "Jack"),
                new Person(5, "king"),
                new Person(2, "Bob"),
                new Person(9, "Tom"),
                new Person(4, "Tim")};
        // 5.max使用
        System.out.println("6.");
        List<Person> list6 = Arrays.asList(peos);
        Person person = list6.stream()
                .max((p, p1) -> (p.getId().compareTo(p1.getId())))
                .get();
        System.out.println(person);

        // 6.min使用
        System.out.println("7.");
        List<Person> list7 = Arrays.asList(peos);
        Person person1 = list7.stream()
                .min((p, p1) -> (p.getId().compareTo(p1.getId())))
                .get();
        System.out.println(person1);

        // 8.将结果集转换为String、Set、TreeSet
        System.out.println("8.");
        List<Person> list8 = Arrays.asList(peos);
        String nameStr = list8.stream()
                .map(Person::getName)
                .collect(Collectors.joining(";"));      // 结果存放在String中
        System.out.println("nameStr:" + nameStr);

        Set<String> nameSet = list8.stream()
                .map(Person::getName)
                .collect(Collectors.toSet());               // 结果存放在Set中
        Arrays.asList(nameSet).stream()
                .forEach(System.out::print);
        System.out.println();

        TreeSet<String> nameTreeSet = list8.stream()
                .map(Person::getName)
                .collect(Collectors.toCollection(TreeSet::new));    // 结果存放在ThreeSet中
        Arrays.asList(nameTreeSet).stream()
                .forEach(System.out::print);
        System.out.println();

        // 9.Streams 还可以是并行的(parallel)
        System.out.println("9.");
        int sum = Arrays.asList(peos).parallelStream()
                .mapToInt(p -> p.getId())
                .sum();
        System.out.println("sum:" + sum);

        // 10.可以使用summaryStatistics方法获得stream 中元素的各种汇总数据
        System.out.println("10.");
        IntSummaryStatistics intSummaryStatistics = Arrays.asList(peos).stream()
                .mapToInt(p -> p.getId())
                .summaryStatistics();
        System.out.println("intSummaryStatistics -> getMax:" + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics -> getMin:" + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics -> getAvg:" + intSummaryStatistics.getAverage());
        System.out.println("intSummaryStatistics -> getCut:" + intSummaryStatistics.getCount());
        System.out.println("intSummaryStatistics -> getSum:" + intSummaryStatistics.getSum());

    }

}
