package com.cyq.lambda_java8.demo01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 演示类
 */
public class LambdaDemoTest02 {

    /**
     * 案例二：假设有一个人员集合，分别找出其中年龄大于20和工资大于10000的人
     */

    private List<Person> list = Arrays.asList(
            new Person(1, "李四", 23, 90000d),
            new Person(2, "赵柳", 18, 30000d),
            new Person(3, "英勇", 32, 4000d));

    /**
     * 普通方式：找出年龄大于20的人员
     */
    public void test1() {
        List<Person> newList = new ArrayList<>();
        for(Person person : list) {
            if (person.getAge() > 20) {
                newList.add(person);
            }
        }
    }

    /**
     * 普通方式：找出工资大于10000的人员
     */
    public void test2() {
        List<Person> newList = new ArrayList<>();
        for(Person person : list) {
            if (person.getSalary() > 10000) {
                newList.add(person);
            }
        }
    }

    /**
     * 使用普通方式的弊端：代码分散，没有统一的管理，不便于扩展使用。
     * 使用策略设计模式可以便于扩展使用
     */
    /**
     * 策略设计模式：找出年龄大于20的人员
     */
    public void test3() {
        MyPredicteByAge predicteByAge = new MyPredicteByAge();
        List<Person> newList = filterPerson(list, predicteByAge);
    }

    /**
     * 策略设计模式：找出工资大于10000的人员
     */
    public void test4() {
        MyPredicteBySalary predicteBySalary = new MyPredicteBySalary();
        List<Person> newList = filterPerson(list, predicteBySalary);
    }

    /**
     * 优化方式一：使用策略模式的匿名内部类的形式
     */
    public void test5() {
        List<Person> newList = filterPerson(list, new MyPredicte() {
            @Override
            public boolean filterData(Person person) {
                return person.getAge() > 20;
            }
        });
    }

    /**
     * 优化方式二：使用Lambda表达式的形式
     */
    public void test6() {
        List<Person> newList = filterPerson(list, (p) -> p.getAge() > 20);
    }

    /**
     * 优化方式三：使用Lambda表达式的形式
     */
    public void test7() {
        list.stream()
            .filter((e) -> e.getAge() > 20)     // 过滤数据
            .map(e -> e.getUserName())          // Map转换
            .forEach(System.out::println);      // 打印数据
    }

    /**
     * 根据过滤器过滤人员信息方法
     * @param list
     * @param myPredicte
     * @return
     */
    private List<Person> filterPerson(List<Person> list, MyPredicte myPredicte) {
        List<Person> newList = new ArrayList<>();
        for (Person person : list) {
            if (myPredicte.filterData(person)) {
                newList.add(person);
            }
        }
        return newList;
    }

}
