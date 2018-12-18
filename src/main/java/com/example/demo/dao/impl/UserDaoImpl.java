package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<UserVO> getAllUser() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserVO> userVOs = mapper.getAllUser();
        session.close();

        return userVOs;
    }

    @Override
    public Optional<UserVO> getUserBySeq(int seq) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserVO userVO = mapper.getUser(seq);
        session.close();

        return Optional.ofNullable(userVO);
    }

    @Override
    public Optional<UserVO> getUserByUserCode(String userCode) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserVO userVO = mapper.getUserByUserCode(userCode);
        session.close();

        return Optional.ofNullable(userVO);
    }

    @Override
    public void save(UserVO userVO) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.insertUser(userVO);
        session.commit();
        session.close();
    }

    @Override
    public void update(UserVO userVO) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.updateUser(userVO);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int seq) {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.deleteUser(seq);
        session.commit();
        session.close();
    }
}
