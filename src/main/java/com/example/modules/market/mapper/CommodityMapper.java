package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
    @Select("SELECT c.is_rec,c.gmt_create,c.id,u.nickname,u.headaddress,c.price,c.introduce,c.gmt_create,c.want\n" +
            "FROM commodity c LEFT JOIN `user` u ON u.id=c.user_id")
    List<CommodityVO> getAllCommodityVo();

    //查询我发布的商品
    @Select("SELECT c.is_rec,c.id,u.nickname,u.headaddress,c.price,c.introduce,c.gmt_create,c.want\n" +
            "FROM commodity c LEFT JOIN `user` u ON u.id=c.user_id WHERE u.id= #{userid}")
    List<CommodityVO> getMyCommodityVo(String userid);

    //根据先要商品id查商品
    @Select("SELECT c.is_rec,c.id,u.nickname,u.headaddress,c.price,c.introduce,c.gmt_create,c.want\n" +
            "FROM commodity c LEFT JOIN `user` u ON u.id=c.user_id WHERE c.id= #{commodityId}")
    CommodityVO getMyWantCommodityVoByCId(String commodityId);

    //查询我想要的商品byUserid
    @Select("")
    List<CommodityVO> getMyWantCommodity(String userid);
}
