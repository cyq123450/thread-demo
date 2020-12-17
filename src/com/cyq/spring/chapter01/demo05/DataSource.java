package com.cyq.spring.chapter01.demo05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("d1")
public class DataSource {

    @Value("${player.url}")
    private String url;
    @Value("${player.info}")
    private String info;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "url='" + url + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
