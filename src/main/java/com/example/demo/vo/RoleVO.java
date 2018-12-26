package com.example.demo.vo;

public class RoleVO {

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "roleId='" + roleId + '\'' +
                '}';
    }
}
