package com.cyq.compare.comparable.demo01;

/**
 * Comparable是对同一个类的不同实例的同一个属性值进行对比
 */
public class Dog implements Comparable<Dog>{

    private Integer id;
    private String name;

    public Dog(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Dog o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() == o.getId()) {
            return 0;
        } else {
            return -1;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
