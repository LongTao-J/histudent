package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.CommodityCollection;
import com.example.modules.market.entity.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityCollectionMapper extends BaseMapper<CommodityCollection> {

    //查询商品的收藏数量
    @Select("SELECT count(*) from commodity_collection WHERE commodity_collection.commodity_id= #{commodityId}")
    Integer getCommodityCountMapper(String commodityId);

    //查询我的收藏
    @Select("SELECT c.is_rec,c.id,u.nickname,u.headaddress,c.price,c.introduce,p.gmt_create,c.want\n" +
            "            FROM commodity c LEFT JOIN commodity_collection p ON p.commodity_id=c.id\n" +
            "\t\t\t\t\t\tLEFT JOIN `user` u ON u.id=p.user_id\n" +
            "\t\t\t\t\t\tWHERE u.id= #{userid}")
    List<CommodityVO> getMyCollection(String userid);

    //根据商品id查发布者的名字
    @Select("SELECT u.nickname FROM `user` u,commodity c\n" +
            "WHERE u.id=c.user_id AND c.id=#{id}")
    String getUserNickName(String id);
}
