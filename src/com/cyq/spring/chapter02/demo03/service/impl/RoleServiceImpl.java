package com.cyq.spring.chapter02.demo03.service.impl;

import com.cyq.spring.chapter02.demo03.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public void printRole() {
        System.out.println("printRole()...");
    }
}
