package com.example.demo.domain;

import com.example.demo.vo.UserVO;
import org.springframework.beans.BeanUtils;

public class User {

    private Integer userSeq;
    private String userCode;
    private String pcode;
    private String roleId;

    public UserVO getUserVO() {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(this, vo, "userSeq");
        vo.setRoleId("ROLE_" + this.getRoleId().toUpperCase());

        return vo;
    }

    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userSeq=" + userSeq +
                ", userCode='" + userCode + '\'' +
                ", pcode='" + pcode + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
