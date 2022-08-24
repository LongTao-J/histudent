package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.modules.market.entity.po.CommodityWant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityWantMapper extends BaseMapper<CommodityWant> {

    @Select("SELECT * FROM commodity_want ${ew.customSqlSegment}")
    List<CommodityWant> selectPageVoLt(IPage<CommodityWant> page, @Param("ew") Wrapper<CommodityWant> queryWrapper);
}
