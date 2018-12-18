package com.example.demo.dao;

import com.example.demo.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserVO> getAllUser();

    Optional<UserVO> getUserBySeq(int seq);

    Optional<UserVO> getUserByUserCode(String userCode);

    void save(UserVO userVO);

    void update(UserVO userVO);

    void delete(int seq);
}
