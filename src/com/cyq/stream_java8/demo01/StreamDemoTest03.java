package com.cyq.stream_java8.demo01;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream操作的三步骤：
 *      1.创建stream
 *      2.中间操作
 *      3.终止操作
 */
public class StreamDemoTest03 {

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
     * 3.终止操作
     *      查找与匹配
     *          allMatch : 检查是否匹配所有元素
     *          anyMatch : 检查是否至少匹配一个元素
     *          noneMatch : 检查是否没有匹配所有元素
     *          findFirst : 返回第一个元素
     *          findAny : 返回当前流中任意元素
     *          count : 返回流中元素的总个数
     *          max : 返回流中最大值
     *          min : 返回流中最小值
     */
    @Test
    public void test1() {
        // allMatch : 检查是否匹配所有元素
        boolean retVal1 = students.stream()
                .allMatch((s) -> s.getAge() > 18);
        System.out.println("allMatch age > 18 : " + retVal1);

        // anyMatch : 检查是否至少匹配一个元素
        boolean retVal2 = students.stream()
                .anyMatch((s) -> s.getSalary() > 10000);
        System.out.println("anyMatch salary > 10000 : " + retVal2);

        // noneMatch : 检查是否没有匹配所有元素
        boolean retVal3 = students.stream()
                .noneMatch((s) -> s.getAge() >= 60);
        System.out.println("noneMatch age >= 60 : " + retVal3);

        // findFirst : 返回第一个元素
        Student student1 = students.stream()
                .findFirst()
                .get();
        System.out.println("findFirst : " + student1);

        // findAny : 返回当前流中任意元素
        Student student2 = students.stream()
                .findAny()
                .get();
        System.out.println("findAny : " + student2);

        // count : 返回流中元素的总个数
        long count = students.stream()
                .count();
        System.out.println("count : " + count);

        // max : 返回流中最大值
        Optional<Student> max = students.stream()
                .max((s1, s2) -> s1.getSalary().compareTo(s2.getSalary()));
        System.out.println("max : " + max.get());

        // min : 返回流中最小值
        Optional<Student> min = students.stream()
                .min((s1, s2) -> s1.getSalary().compareTo(s2.getSalary()));
        System.out.println("min : " + min.get());
    }

    /**
     * 3.终止操作
     *      归约
     *          reduce(T identity, BinaryOperator)/reduce(Binaryoperator) : 可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test2() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // T reduce(T identity, BinaryOperator<T> accumulator); 会把identity参数的值作为起始值，第一次为：identity+1 -> x 第二次为x+2 -> x
        Integer num1 = nums.stream()
                .reduce(0, (x, y) -> x + y);    // 该返回值是一个数，因为有起始值，所以返回值不可能为空
        System.out.println("num1 : " + num1);

        /**
         * map和reduce联合使用又叫map-reduce模式
         */
        // 计算员工工资总和
        Optional<Double> num2 = students.stream()       // 该返回值被封装到了Optional对象中，这是因为返回值有可能为空
                .map(Student::getSalary)
                .reduce(Double::sum);
        System.out.println("num2 : " + num2.get());
    }

    /**
     * 3.终止操作
     *      收集
     *          collect : 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
     */
    @Test
    public void test3() {
        // 提取名字到List集合内
        List<String> collect1 = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);

        System.out.println("--------------------");

        // 提取名字到HashSet中
        HashSet<String> collect2 = students.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);

        System.out.println("--------------------");

        // 提取学生总数
        Long count1 = students.stream()
                .collect(Collectors.counting());
        System.out.println("count1 : " + count1);

        System.out.println("--------------------");

        // 提取工资平均值
        Double avg = students.stream()
                .collect(Collectors.averagingDouble(Student::getSalary));
        System.out.println("avg : " + avg);

        System.out.println("--------------------");

        // 提取工资总和
        Double sum = students.stream()
                .collect(Collectors.summingDouble(Student::getSalary));
        System.out.println("sum : " + sum);

        System.out.println("--------------------");

        // 分组
        Map<String, List<Student>> collect = students.stream()
                .collect(Collectors.groupingBy(Student::getName));
        System.out.println(collect);

        System.out.println("--------------------");

        // 多级分组(组内分组)
        Map<String, Map<Double, List<Student>>> collect3 = students.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.groupingBy(Student::getSalary)));
        System.out.println(collect3);

        System.out.println("--------------------");

        Map<String, List<Student>> collect4 = students.stream()
                .collect(Collectors.groupingBy(s -> {
                    if (s.getAge() < 20) {
                        return "青年";
                    } else {
                        return "大人";
                    }
                }));
        System.out.println(collect4);
    }

    // 分区(分片)
    @Test
    public void test4() {
        Map<Boolean, List<Student>> collect = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getSalary() > 10000));
        System.out.println(collect);

        System.out.println("-----------------------------");

        DoubleSummaryStatistics statistics = students.stream()
                .collect(Collectors.summarizingDouble(Student::getSalary));
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

        System.out.println("-----------------------------");

        String strs = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(",", "---", "==="));
        System.out.println(strs);
    }

}
