package com.cyq.spring.chapter01.demo06;

import org.springframework.beans.factory.annotation.Value;

public class User {

    @Value("${user.id}")
    private String id;
    @Value("${user.userName}")
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
