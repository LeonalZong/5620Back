package com.backend5620.mapper;

import com.backend5620.object.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 根据用户名和密码获取用户信息
    @Select("SELECT * FROM Users WHERE username = #{username} AND password = #{password}")
    Users getByUsernameAndPassword(Users user);

    // 根据用户名获取用户信息
    @Select("SELECT * FROM Users WHERE username = #{username}")
    Users getByUsername(String username);

    // 插入新用户时需要确保与数据库字段一致
    @Insert("INSERT INTO Users (username, email, password, registration_date) " +
            "VALUES (#{username}, #{email}, #{password}, CURRENT_TIMESTAMP)")
    void insertUser(Users user);
}