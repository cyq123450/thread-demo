package com.cyq.stream_java8.demo02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 练习
 */
public class StreamDemoTest01 {

    private List<Employee> employees = new ArrayList<>();

    @Before
    public void before() {
        employees.add(new Employee("Jack", 23, 100D, Employee.Status.FREE));
        employees.add(new Employee("King", 26, 500D, Employee.Status.WORK));
        employees.add(new Employee("Mary", 23, 700D, Employee.Status.VACATION));
        employees.add(new Employee("Jack", 27, 500D, Employee.Status.FREE));
        employees.add(new Employee("Tom", 31, 900D, Employee.Status.WORK));

    }

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表？
     *      给定[1, 2, 3, 4, 5]返回[1, 4, 9, 16, 25]
     */
    @Test
    public void test1() {
        List<Integer> originList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> distList = originList.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("originList : " + originList);
        System.out.println("distList : " + distList);
    }

    /**
     * 怎样用map和reduce方法数一数流中有多少个Employee呢？
     */
    @Test
    public void test2() {
        Optional<Integer> optional = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println("employee总数为 : " + optional.get());
    }

}
