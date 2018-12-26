package com.example.demo.mapper;

import com.example.demo.vo.RoleVO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {

    @Insert("INSERT INTO DEMO_ROLE(ROLE_ID) VALUES(#{roleId})")
    void insertRole(RoleVO roleVO);
}
