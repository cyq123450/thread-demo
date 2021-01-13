package com.cyq.stream_java8.demo01;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream操作的三步骤：
 *      1.创建stream
 *      2.中间操作
 *      3.终止操作
 */
public class StreamDemoTest02 {

    private List<Student> students = new ArrayList<Student>();

    @Before
    public void before() {
        students.add(new Student(1, "Jack", 23, 10000D));
        students.add(new Student(2, "Tom", 13, 7000D));
        students.add(new Student(3, "King", 31, 60000D));
        students.add(new Student(4, "Joe", 15, 2000D));
        students.add(new Student(4, "Joe", 15, 2000D));
    }

    /**
     * 2.中间操作
     *      筛选与切片
     *          filter : 接收lambda，从流中排除某些元素
     *          limit : 截断流，使其元素不超过指定数量
     *          skip(n) : 跳过元素，返回一个扔掉了前n个元素的流，若流中的元素不满足n个，则返回一个空流。与limit互补
     *          distinct : 筛选。通过流生成元素的hashcode()和equals()去除重复元素
     */

    /**
     *  filter
     */
    @Test
    public void test1() {
        // 内部迭代：由lambda帮助我们去迭代
        Stream<Student> studentStream = students.stream().filter((s) -> s.getAge() > 20);
        studentStream.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        // 外部迭代
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * limit
     *      注意：在使用limit时，当获取的数据量满足limit中的数量时就不再进行遍历了
      */
    @Test
    public void test2() {
        Stream<Student> studentStream = students.stream().filter((s) -> {
            System.out.println("中间过程");
            return s.getSalary() > 1000;
        }).limit(1);
        studentStream.forEach(System.out::println);
    }

    /**
     * skip
     *      skip会再遍历完所有的数据，找到满足条件的数据扔掉前几个。
     */
    @Test
    public void test3() {
        students.stream()
                .filter((s) -> {
            System.out.println("中间过程");
            return s.getAge() > 20;
        }).skip(1).forEach(System.out::println);
    }

    /**
     * distinct
     *      distinct去重的引用类型必须重写hashcode和equals方法，否则可能去重不生效
     */
    @Test
    public void test4() {
        Stream<Student> studentStream = students.stream()
                .filter((s) -> s.getAge() > 10)
                .distinct();
        studentStream.forEach(System.out::println);
    }

    /**
     * 2.中间操作
     *      映射
     *          map : 接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新元素。
     *          flatMap : 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    /**
     * map
     */
    @Test
    public void test5() {
        List<String> strings = Arrays.asList("asd", "fgh", "hjk");
        Stream<String> stringStream1 = strings.stream()
                .map((s) -> s.toUpperCase());
        stringStream1.forEach(System.out::println);
        System.out.println("--------------------------");

        /**
         * 流中存储着流
         */
        Stream<Stream<Character>> streamStream2 = strings.stream()
                .map(this::getCharacter);
        streamStream2.
                forEach((s) -> s.forEach(System.out::println));
    }

    /**
     * flatMap
     */
    @Test
    public void test6() {
        List<String> strings = Arrays.asList("asd", "fgh", "hjk");
        Stream<Character> characterStream = strings.stream()
                .flatMap(this::getCharacter);
        characterStream.forEach(System.out::println);
    }

    /**
     * map和flatMap的区别：
     *      map和flatMap的区别主要在于map是把一个个流加入到另一个流中，而flatMap是把每一个流中的元素取出来放到一个流中
     *      map相当有add()方法
     *      flatMap相当于addAll()方法
     */
    @Test
    public void test7() {
        List<String> list1 = Arrays.asList("a", "b", "c");
        List list2 = new ArrayList();
        list2.add("1");
        list2.add("2");
        list2.add(list1);
        // list1.addAll(list2);
        System.out.println(list2);
    }

    public Stream<Character> getCharacter(String str) {
        List list = new ArrayList<Character>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 2.中间操作
     *      排序
     *          sorted : 自然排序(Comparable)
     *          sorted(Comparator com) : 定制排序
     */
    @Test
    public void test8() {
        List<String> strings = Arrays.asList("aaa", "fff", "ttt", "ppp");
        strings.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        students.stream()
                .sorted((s1, s2) -> {
                    if (s1.getAge() > s2.getAge()) {
                        return 2;
                    } else if (s1.getAge().equals(s2.getAge())) {
                        return s1.getSalary().compareTo(s1.getSalary());
                    } else {
                        return -2;
                    }
                })
                .forEach(System.out::println);
    }

}
