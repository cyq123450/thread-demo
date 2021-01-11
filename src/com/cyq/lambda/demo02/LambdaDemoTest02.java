package com.cyq.lambda.demo02;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaDemoTest02 {

    /**
     * 案例一：使用Collection.sort()方法对Employee类实例进行排序，先按年龄排，年龄相等再按姓名排。
     */
    @Test
    public void test1() {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Jack", 23),
                new Employee(2, "Tom", 23),
                new Employee(3, "King", 12)
        );
        Collections.sort(employees, (x, y) -> {
            if (x.getAge() > y.getAge()) {
                return 2;
            } else if (x.getAge().intValue() == y.getAge().intValue()) {
                return x.getUserName().compareTo(y.getUserName());
            }  else {
                return -2;
            }
        });
        employees.forEach((e) -> System.out.println(e));
    }

    public void test2() {

    }

}
