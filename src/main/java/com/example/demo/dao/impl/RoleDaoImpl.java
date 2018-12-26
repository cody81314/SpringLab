package com.example.demo.dao.impl;

import com.example.demo.dao.RoleDao;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleMapper mapper;

    @Override
    public void save(RoleVO roleVO) {
        mapper.insertRole(roleVO);
    }
}
