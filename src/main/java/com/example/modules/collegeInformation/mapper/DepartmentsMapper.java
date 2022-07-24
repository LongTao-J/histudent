package com.example.modules.collegeInformation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentsMapper extends BaseMapper<Departments> {
    List<Departments> selectList();
}
