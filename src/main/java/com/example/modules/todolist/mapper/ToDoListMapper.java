package com.example.modules.todolist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.todolist.entity.Todolist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ToDoListMapper extends BaseMapper<Todolist> {

    @Select("SELECT * from todolist WHERE user_id=#{userid} ORDER BY create_time DESC")
    List<Todolist> getToDo(String userid);
}
