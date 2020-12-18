package com.cyq.spring.chapter02.demo01;

import com.cyq.spring.chapter02.demo01.entity.Role;
import com.cyq.spring.chapter02.demo01.interceptor.Interceptor;
import com.cyq.spring.chapter02.demo01.interceptor.impl.InterceptorImpl;
import com.cyq.spring.chapter02.demo01.service.RoleService;
import com.cyq.spring.chapter02.demo01.service.impl.RoleServiceImpl;
import com.cyq.spring.chapter02.demo01.util.ProxyBeanFactory;

public class Main {

    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();
        Interceptor interceptor = new InterceptorImpl();
        RoleService service = ProxyBeanFactory.getBean(roleService, interceptor);
        Role role = new Role(1L, "Jack", "Jack是一个科学家");
        service.printRoleInfo(role);
    }

}
