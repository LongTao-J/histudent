package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.WantLikeCountDTO;
import com.example.modules.market.entity.dto.WritCommentDTO;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.service.CommodityCommentService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/comment")
public class CommodityCommentController {

    @Autowired
    CommodityCommentService commodityCommentServiceImpl;

    @Autowired
    RedisTemplate redisTemplate;

    //查看商品所有评论
    @GetMapping("/getAllComment/{commodityId}")
    @CrossOrigin
    public R<List<CommentVo>> getAllCom(@PathVariable("commodityId") String commodityId){
        try {
            List<CommentVo> allCommBycIdSer = commodityCommentServiceImpl.getAllCommBycIdSer(commodityId);
            return R.success(allCommBycIdSer);
        }catch (Exception r){
            return R.error();
        }
    }

    //写评论
    @PostMapping("/addComment")
    @CrossOrigin
    public R<String> addCommentCont(@RequestBody WritCommentDTO writCommentDTO){
        try {
            commodityCommentServiceImpl.WriteCommentServiec(writCommentDTO);
            return R.success("写评论成功");
        }catch (Exception e){
            return R.error();
        }
    }

    //删除评论
    @DeleteMapping("/deleteComment/{commentId}")
    @CrossOrigin
    public R<String> deleteCommentCont(@PathVariable("commentId") String commentId){
        try {
            commodityCommentServiceImpl.removeById(commentId);
            return R.success("成功");
        }catch (Exception e){
            return R.error();
        }
    }

}
