package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.CommodityImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityImageMapper extends BaseMapper<CommodityImage> {

    //根据商品id查图片
    @Select("select commodity_img from `commodity-image` WHERE commodity_id= #{commodityId}")
    List<String> getAllCommodityImg(String commodityId);
}
