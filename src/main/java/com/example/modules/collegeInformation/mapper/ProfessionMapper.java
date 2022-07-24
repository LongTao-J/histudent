package com.example.modules.collegeInformation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.collegeInformation.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfessionMapper extends BaseMapper<Profession> {
    List<Profession> selectList();
}
