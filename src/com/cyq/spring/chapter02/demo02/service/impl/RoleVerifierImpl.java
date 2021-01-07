package com.cyq.spring.chapter02.demo02.service.impl;

import com.cyq.spring.chapter02.demo02.entity.Role;
import com.cyq.spring.chapter02.demo02.service.RoleVerifier;

public class RoleVerifierImpl implements RoleVerifier {
    @Override
    public boolean verify(Role role) {
        return role != null;
    }
}
