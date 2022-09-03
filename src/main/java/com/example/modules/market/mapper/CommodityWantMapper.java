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


    //根据userid查商品id
    @Select("SELECT commodity_id FROM commodity_want WHERE user_id= #{userid}")
    List<String> getAllwantByUId(String userid);

    //查询想要的个数
    @Select("SELECT count(*) FROM commodity_want WHERE commodity_want.commodity_id= #{commodityId}")
    Integer getWantCountMapper(String commodityId);
}
