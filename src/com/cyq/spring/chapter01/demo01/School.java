package com.cyq.spring.chapter01.demo01;

public class School {

    private String name;
    private User user;

    public School() {
    }

    public School(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
