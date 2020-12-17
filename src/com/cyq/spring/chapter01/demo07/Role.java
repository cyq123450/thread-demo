package com.cyq.spring.chapter01.demo07;

public class Role {

    private String id;
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
