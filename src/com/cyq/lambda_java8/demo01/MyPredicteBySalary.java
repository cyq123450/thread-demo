package com.cyq.lambda_java8.demo01;

/**
 * 通过工资过滤实现类(过滤工资大于10000的人员)
 */
public class MyPredicteBySalary implements MyPredicte{
    @Override
    public boolean filterData(Person person) {
        return person.getSalary() > 10000;
    }
}
