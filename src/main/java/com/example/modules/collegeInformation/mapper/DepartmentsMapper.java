package com.example.modules.collegeInformation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentsMapper extends BaseMapper<Departments> {
    List<Departments> selectList();

    @Select("select * from departments where sch_id = #{id} and name = #{name}")
    Departments queryByIdAndName(@Param("id") String id, @Param("name") String name);
}
