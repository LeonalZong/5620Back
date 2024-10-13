package com.backend5620.mapper;

import com.backend5620.object.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM Users WHERE username = #{username} AND password = #{password}")
    Users getByUsernameAndPassword(Users user);

    @Select("SELECT * FROM Users WHERE username = #{username}")
    Users getByUsername(String username);

    @Insert("INSERT INTO Users (name, email, phone, accessType, username, password) " +
            "VALUES (#{name}, #{email}, #{phone}, #{accessType}, #{username}, #{password})")
    void insertUser(Users user);
}
