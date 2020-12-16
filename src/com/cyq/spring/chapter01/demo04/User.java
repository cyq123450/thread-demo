package com.cyq.spring.chapter01.demo04;

/**
 * @author cyq
 * @description
 * @date 2020/12/16 19:42
 */
public class User {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
            "userName='" + userName + '\'' +
            '}';
    }
}
