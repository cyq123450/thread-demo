package com.cyq.spring.chapter01.demo03.service.impl;

import com.cyq.spring.chapter01.demo03.service.TrafficService;
import org.springframework.stereotype.Component;

@Component
public class PlaneServiceImpl implements TrafficService {
    @Override
    public void go() {
        System.out.println("飞机飞...");
    }
}
