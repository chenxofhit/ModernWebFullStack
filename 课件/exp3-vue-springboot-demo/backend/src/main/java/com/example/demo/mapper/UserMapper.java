package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    
    @Select("SELECT * FROM users")
    List<User> findAll();
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Integer id);
    
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
    
    @Select("INSERT INTO users(username, password, email, avatar) VALUES(#{username}, #{password}, #{email}, #{avatar}) RETURNING *")
    User insert(User user);
    
    @Update("UPDATE users SET username = #{username}, email = #{email}, avatar = #{avatar} WHERE id = #{id} AND username IS NOT NULL")
    int update(User user);
    
    @Update("UPDATE users SET password = #{newPassword} WHERE id = #{id}")
    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);
    
    @Update("UPDATE users SET avatar = #{avatar} WHERE id = #{id}")
    int updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);
} 