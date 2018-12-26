package com.example.demo.mapper;

import com.example.demo.vo.UserVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Results(id = "userResult", value = {
            @Result(property = "userSeq", column = "USER_SEQ"),
            @Result(property = "userCode", column = "USER_CODE"),
            @Result(property = "pcode", column = "PCODE"),
            @Result(property = "roleId", column = "ROLE_ID"),
    })
    @Select("SELECT * FROM DEMO_USER WHERE USER_SEQ = #{userSeq}")
    UserVO getUser(int userSeq);

    @ResultMap("userResult")
    @Select("SELECT * FROM DEMO_USER WHERE USER_CODE = #{userCode}")
    UserVO getUserByUserCode(String userCode);

    @ResultMap("userResult")
    @Select("SELECT * FROM DEMO_USER")
    List<UserVO> getAllUser();

    @Insert("INSERT INTO DEMO_USER(USER_CODE,PCODE,ROLE_ID) VALUES(#{userCode}, #{pcode}, #{roleId})")
    void insertUser(UserVO userVO);

    @Update("UPDATE DEMO_USER SET USER_CODE=#{userCode}, PCODE=#{pcode}, ROLE_ID=#{roleId} WHERE USER_SEQ =#{userSeq}")
    void updateUser(UserVO userVO);

    @Delete("DELETE FROM DEMO_USER WHERE USER_SEQ =#{userSeq}")
    void deleteUser(int userSeq);
}
