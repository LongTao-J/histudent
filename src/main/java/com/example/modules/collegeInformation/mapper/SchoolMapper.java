package com.example.modules.collegeInformation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.collegeInformation.pojo.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolMapper extends BaseMapper<School> {

    List<School> selectList();


    //龙涛临时加的
    @Select("SELECT name from school")
    public List<String> getSchoolname();

    //龙涛临时加的
    @Select("SELECT `name` FROM school WHERE `name` LIKE #{shu}")
    public List<String> selectSchoolname(String shu);
}
