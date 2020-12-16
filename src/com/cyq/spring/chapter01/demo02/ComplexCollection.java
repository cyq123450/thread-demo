package com.cyq.spring.chapter01.demo02;

import java.util.*;

public class ComplexCollection {

    private String str;
    private List<String> list;
    private Set<String> set;
    private String[] array;
    private Properties properties;
    private Map<String, String> map;

    public ComplexCollection() {
    }

    public ComplexCollection(String str, List<String> list, Set<String> set, String[] array, Properties properties, Map<String, String> map) {
        this.str = str;
        this.list = list;
        this.set = set;
        this.array = array;
        this.properties = properties;
        this.map = map;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ComplexCollection{" +
                "str='" + str + '\'' +
                ", list=" + list +
                ", set=" + set +
                ", array=" + Arrays.toString(array) +
                ", properties=" + properties +
                ", map=" + map +
                '}';
    }
}
