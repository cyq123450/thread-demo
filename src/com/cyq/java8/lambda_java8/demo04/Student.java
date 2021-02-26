package com.cyq.java8.lambda_java8.demo04;

/**
 * Student实体类
 */
public class Student {

    private Integer id;
    private String userName;
    private Integer age;

    public Student() {
    }

    public Student(String userName) {
        this.userName = userName;
    }

    public Student(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public Student(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
