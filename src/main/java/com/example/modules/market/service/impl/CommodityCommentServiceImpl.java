package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.dto.WritCommentDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.entity.vo.CommodityCommentVVo;
import com.example.modules.market.mapper.CommodityCommentMapper;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.service.CommodityCommentService;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityCommentServiceImpl extends ServiceImpl<CommodityCommentMapper, CommodityComment> implements CommodityCommentService {

    @Autowired
    CommodityCommentMapper commodityCommentMapper;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    CommodityMapper commodityMapperImpl;

    @Override
    public List<CommentVo> getAllCommBycIdSer(String commodityId) {
        try {
            List<CommentVo> allCommentByCid = new ArrayList<>();

            List<CommodityCommentVVo> commodityComments = commodityCommentMapper.getCommodityCommentVVo(commodityId);

            for (int i=0;i<commodityComments.size();i++){
                CommentVo commentVo=new CommentVo();
                commentVo.setComment(commodityComments.get(i));
                User user=new User();
                user= userServiceImpl.getById(commodityComments.get(i).getUserId());
                commentVo.setUserHead(user.getHeadaddress());
                commentVo.setUserNickname(user.getNickname());
                allCommentByCid.add(commentVo);
            }
            return allCommentByCid;
        }catch (Exception e){
            return null;
        }
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

    @Override
    public Integer deleteComment(String commentId) {
        try {
            String currenId=userServiceImpl.getTokenUser().getId();
            CommodityComment comment = this.getById(commentId);
            Commodity commodity = commodityMapperImpl.selectById(comment.getCommodityId());
            if (commodity.getUserId().equals(currenId)){
                return 1;
            }else {
                return 0;
            }
        }catch (Exception e){
            return 2;
        }
    }

}
