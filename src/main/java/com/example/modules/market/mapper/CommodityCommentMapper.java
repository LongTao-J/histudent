package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.entity.vo.CommodityCommentVVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityCommentMapper extends BaseMapper<CommodityComment> {

    @Select("SELECT u.id AS userId,u.nickname AS userNickname,u.headaddress AS userHead,c.id,c.commodity_id As commodityId,c.comment As content from `user` u LEFT JOIN `commodity_comment` c ON c.user_id=u.id WHERE c.commodity_id= #{commodityId}")
    List<CommentVo> getAllCommentByCid(String commodityId);

    //根据商品id查评论个数
    @Select("SELECT count(*)\n" +
            "from `user` u LEFT JOIN `commodity_comment` c ON c.user_id=u.id WHERE c.commodity_id= #{commodityId}")
    Integer getCommentCountByCid(String commodityId);

    //查询商品评论
    @Select("select c.id,c.`comment` AS content,c.user_id,c.commodity_id,c.gmt_create,c.gmt_modified FROM commodity_comment c WHERE c.commodity_id=#{commodityId} ORDER BY gmt_create desc")
    List<CommodityCommentVVo> getCommodityCommentVVo(String commodityId);
}
