package com.cyq.spring.chapter01.demo07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person")
public class Person {

    @Value("#{user}")
    private User user;
    @Value("#{user.id}")
    private Integer id;
    @Value("#{user.getName().toString()}")
    private String name1;
    @Value("#{user.getName()?.toString()}")
    private String name2;

    @Value("#{T(Math).PI}")     // 获取类的静态方法或属性
    private String pi1;
    @Value("#{T(java.lang.Math).PI}")
    private String pi2;
    @Value("#{T(java.lang.Math).random()}")
    private Double num;

    // SpEL运算
    @Value("#{user.id + 1}")
    private int num1;
    @Value("#{user.id + user.name}")
    private String str;
    @Value("#{user.id == 1001}")
    private boolean idEqual1;
    @Value("#{user.id eq 1001}")
    private boolean isIdEqual2;
    @Value("#{user.id >= 100}")
    private boolean isMore;
    @Value("#{user.name == null ? '12' : '45'}")
    private String note3;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPi1() {
        return pi1;
    }

    public void setPi1(String pi1) {
        this.pi1 = pi1;
    }

    public String getPi2() {
        return pi2;
    }

    public void setPi2(String pi2) {
        this.pi2 = pi2;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isIdEqual1() {
        return idEqual1;
    }

    public void setIdEqual1(boolean idEqual1) {
        this.idEqual1 = idEqual1;
    }

    public boolean isIdEqual2() {
        return isIdEqual2;
    }

    public void setIdEqual2(boolean idEqual2) {
        isIdEqual2 = idEqual2;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user=" + user +
                ", id=" + id +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", pi1='" + pi1 + '\'' +
                ", pi2='" + pi2 + '\'' +
                ", num=" + num +
                ", num1=" + num1 +
                ", str='" + str + '\'' +
                ", idEqual1=" + idEqual1 +
                ", isIdEqual2=" + isIdEqual2 +
                ", isMore=" + isMore +
                ", note3='" + note3 + '\'' +
                '}';
    }
}
