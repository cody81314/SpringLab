package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserVO> getAllUser() {
        List<UserVO> userVOs = mapper.getAllUser();

        return userVOs;
    }

    @Override
    public Optional<UserVO> getUserBySeq(int seq) {
        UserVO userVO = mapper.getUser(seq);

        return Optional.ofNullable(userVO);
    }

    @Override
    public Optional<UserVO> getUserByUserCode(String userCode) {
        UserVO userVO = mapper.getUserByUserCode(userCode);

        return Optional.ofNullable(userVO);
    }

    @Override
    public void save(UserVO userVO) {
        mapper.insertUser(userVO);
    }

    @Override
    public void update(UserVO userVO) {
        mapper.updateUser(userVO);
    }

    @Override
    public void delete(int seq) {
        mapper.deleteUser(seq);
    }
}
