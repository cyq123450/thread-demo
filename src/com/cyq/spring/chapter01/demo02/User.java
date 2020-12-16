package com.cyq.spring.chapter01.demo02;

public class User {

    private Integer id;
    private String username;
    private String note;

    public User() {
    }

    public User(Integer id, String username, String note) {
        this.id = id;
        this.username = username;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
