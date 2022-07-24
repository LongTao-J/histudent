package com.example.modules.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
