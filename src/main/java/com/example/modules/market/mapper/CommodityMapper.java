package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
    @Select("SELECT c.id,u.nickname,u.headaddress,c.title,c.price,c.introduce,c.count,c.gmt_create,c.want\n" +
            "FROM commodity c LEFT JOIN `user` u ON u.id=c.user_id")
    List<CommodityVO> getAllCommodityVo();
}
