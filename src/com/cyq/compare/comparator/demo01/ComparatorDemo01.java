package com.cyq.compare.comparator.demo01;

import java.util.*;

/**
 * Compatator比较器的使用
 */
public class ComparatorDemo01 {

    public void compartor(String[] strs) {
        List<String> list = Arrays.asList(strs);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"1", "5", "6", "2", "1", "8", "7"};
        System.out.println("排序前：" + Arrays.toString(strs));
        ComparatorDemo01 comparatorDemo01 = new ComparatorDemo01();
        comparatorDemo01.compartor(strs);
        System.out.println("排序后：" + Arrays.toString(strs));

    }

}
