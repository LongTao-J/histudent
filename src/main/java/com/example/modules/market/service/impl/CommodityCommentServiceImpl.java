package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.dto.WritCommentDTO;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.mapper.CommodityCommentMapper;
import com.example.modules.market.service.CommodityCommentService;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityCommentServiceImpl extends ServiceImpl<CommodityCommentMapper, CommodityComment> implements CommodityCommentService {

    @Autowired
    CommodityCommentMapper commodityCommentMapper;

    @Autowired
    UserService userServiceImpl;

    @Override
    public List<CommentVo> getAllCommBycIdSer(String commodity) {
        List<CommentVo> allCommentByCid = commodityCommentMapper.getAllCommentByCid(commodity);
        return allCommentByCid;
    }

    @Override
    public void WriteCommentServiec(WritCommentDTO writCommentDTO) {
        String userId = userServiceImpl.getTokenUser().getId();
        CommodityComment commodityComment=new CommodityComment();
        commodityComment.setComment(writCommentDTO.getComment());
        commodityComment.setCommodityId(writCommentDTO.getCommodityId());
        commodityComment.setUserId(userId);
        commodityCommentMapper.insert(commodityComment);
    }

    @Override
    public Integer getCommentCount(String commodityId) {
        try {
            Integer commentCountByCid = commodityCommentMapper.getCommentCountByCid(commodityId);
            return commentCountByCid;
        }catch (Exception e){
            return null;
        }
    }
}
