package com.example.modules.collegeInformation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.collegeInformation.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProfessionMapper extends BaseMapper<Profession> {
    List<Profession> selectList();

    @Select("select * from profession where dep_id = #{id} and name = #{name}")
    Profession queryByIdAndName(@Param("id") String id, @Param("name") String name);
}
