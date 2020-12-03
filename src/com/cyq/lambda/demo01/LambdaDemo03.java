package com.cyq.lambda.demo01;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用lambda演示排序
 */
public class LambdaDemo03 {

    public static void main(String[] args) {
        String[] strs = {"12", "23", "34", "1", "45", "21", "34", "234", "123"};

        // 1.使用原来的排序方式进行排序
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        List<String> names = Arrays.asList(strs);
        Collections.sort(names, comparator1);
        System.out.println("1.排序结果:");

        names.forEach(name -> System.out.print(name + "  "));
        System.out.println();

        // 2.使用lambda进行排序
        names = Arrays.asList(strs);
        Comparator<String> comparator2 = (String s1, String s2) -> (s1.compareTo(s2));
        Collections.sort(names, comparator2);
        System.out.println("2.排序结果:");
        names.forEach(name -> System.out.print(name + "  "));
        System.out.println();

        // 3.使用lambda进行排序
        names = Arrays.asList(strs);
        names.sort((String s1, String s2) -> (s1.compareTo(s2)));
        System.out.println("3.排序结果：");
        names.forEach(name -> System.out.print(name + "  "));
    }

}
