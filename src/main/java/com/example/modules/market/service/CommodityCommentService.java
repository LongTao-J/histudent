package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.dto.WritCommentDTO;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;

import java.util.List;

public interface CommodityCommentService extends IService<CommodityComment> {

    //获取所有评论byId
    List<CommentVo> getAllCommBycIdSer(String commodity);

    //写评论
    void WriteCommentServiec(WritCommentDTO writCommentDTO);

    //查询评论的个数
    Integer getCommentCount(String commodityId);
}
