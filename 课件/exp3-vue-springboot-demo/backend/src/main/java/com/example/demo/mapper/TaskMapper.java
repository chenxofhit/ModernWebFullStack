package com.example.demo.mapper;

import com.example.demo.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TaskMapper {
    
    @Select("SELECT * FROM tasks")
    @Results({
        @Result(property = "userId", column = "user_id")
    })
    List<Task> findAll();
    
    @Select("SELECT * FROM tasks WHERE id = #{id}")
    @Results({
        @Result(property = "userId", column = "user_id")
    })
    Task findById(@Param("id") Integer id);
    
    @Select("SELECT * FROM tasks WHERE user_id = #{userId}")
    @Results({
        @Result(property = "userId", column = "user_id")
    })
    List<Task> findByUserId(@Param("userId") Integer userId);
    
    @Insert("INSERT INTO tasks(title, description, status, user_id, attachment) VALUES(#{title}, #{description}, #{status}, #{userId}, #{attachment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Task task);
    
    @Update("UPDATE tasks SET title = #{title}, description = #{description}, status = #{status}, attachment = #{attachment} WHERE id = #{id}")
    int update(Task task);
    
    @Delete("DELETE FROM tasks WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);
} 