package com.example.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityCommentMapper extends BaseMapper<CommodityComment> {

    @Select("SELECT u.id AS userId,u.nickname AS userName,u.headaddress AS headImg,c.id AS commentId,c.comment from `user` u LEFT JOIN `commodity-comment` c ON c.user_id=u.id WHERE c.commodity_id= #{commodityId}")
    List<CommentVo> getAllCommentByCid(String commodityId);
}
