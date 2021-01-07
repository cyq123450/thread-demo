package com.cyq.spring.chapter02.demo02.service.impl;

import com.cyq.spring.chapter02.demo02.entity.Role;
import com.cyq.spring.chapter02.demo02.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public void printRole(Role role) {
        // int i = 1 / 0;  // 模拟异常情况
        System.out.println("RoleServiceImpl printRole()...");
    }
}
