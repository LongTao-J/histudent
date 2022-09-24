package com.example.modules.market.materials.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.materials.pojo.Materials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialsMapper extends BaseMapper<Materials> {

    @Select("select * from materials where name like CONCAT('%',#{name},'%');")
    List<Materials> queryByNameLikeList(@Param("name") String name);
}
