package com.cyq.lambda.demo01;

/**
 * 通过年龄过滤数据实现类(过滤年龄大于50的人员)
 */
public class MyPredicteByAge implements MyPredicte {
    @Override
    public boolean filterData(Person person) {
        return person.getAge() > 50;
    }
}
