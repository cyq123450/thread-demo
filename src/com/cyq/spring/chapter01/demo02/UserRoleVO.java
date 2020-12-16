package com.cyq.spring.chapter01.demo02;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRoleVO {

    private Integer id;
    private Map<User, Role> userRoleMap;
    private List<User> users;
    private Set<Role> roles;

    public UserRoleVO() {
    }

    public UserRoleVO(Integer id, Map<User, Role> userRoleMap, List<User> users, Set<Role> roles) {
        this.id = id;
        this.userRoleMap = userRoleMap;
        this.users = users;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<User, Role> getUserRoleMap() {
        return userRoleMap;
    }

    public void setUserRoleMap(Map<User, Role> userRoleMap) {
        this.userRoleMap = userRoleMap;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UerRoleVO{" +
                "id=" + id +
                ", userRoleMap=" + userRoleMap +
                ", users=" + users +
                ", roles=" + roles +
                '}';
    }
}
