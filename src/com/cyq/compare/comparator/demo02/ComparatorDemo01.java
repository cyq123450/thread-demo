package com.cyq.compare.comparator.demo02;

import java.util.*;

/**
 * Comparator使用演示
 * 对User对象进行排序，排序规则为：
 *      Id优先(从小到大)，年龄次之(从大到小)，工资最后(从大到小)
 */
public class ComparatorDemo01 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 12, 34D));
        users.add(new User(1, 23, 23D));
        users.add(new User(2, 12, 34D));
        users.add(new User(1, 12, 36D));
        users.add(new User(2, 23, 45D));
        users.add(new User(2, 12, 34D));
        users.add(new User(1, 12, 36D));

        System.out.println("排序前：");
        for (User user : users) {
            System.out.println(user);
        }

        Collections.sort(users, new MyComparator());

        System.out.println("排序后：");
        for (User user : users) {
            System.out.println(user);
        }

    }

    public static class MyComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            double ret = o1.getId() - o2.getId();
            if (ret != 0) {
                return ret > 0 ? 3 : -1;
            } else {
                ret = o1.getAge() - o2.getAge();
                if (ret != 0) {
                    return ret < 0 ? 2 : -2;
                } else {
                    ret = o1.getSalary() - o2.getSalary();
                    if (ret != 0) {
                        return ret < 0 ? 1 : -3;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

}
