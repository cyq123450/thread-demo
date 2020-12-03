package com.cyq.compare.comparator.demo02;

/**
 * User实体类
 */
public class User {

    private Integer id;
    private Integer age;
    private Double salary;

    public User(Integer id, Integer age, Double salary) {
        this.id = id;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
