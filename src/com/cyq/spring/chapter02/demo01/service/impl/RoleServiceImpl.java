package com.cyq.spring.chapter02.demo01.service.impl;

import com.cyq.spring.chapter02.demo01.entity.Role;
import com.cyq.spring.chapter02.demo01.service.RoleService;

public class RoleServiceImpl implements RoleService {
    @Override
    public void printRoleInfo(Role role) {
        System.out.println(role);
    }
}
