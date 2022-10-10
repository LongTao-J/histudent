package com.example.modules.recommended.controller;

import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.entity.vo.PostVO;
import com.example.modules.wall.repository.PostLikeRepository;
import com.example.modules.wall.repository.PostRepository;
import com.example.modules.wall.service.PostCollectService;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mushan
 * @date 20/9/2022
 * @apiNote
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/recommended")
public class recommendedController {
    @Autowired
    UserService userServiceImpl;
    @Autowired
    PostRepository postRepositoryImpl;
    @Autowired
    PostLikeRepository postLikeRepositoryImpl;
    @Autowired
    PostCollectService postCollectServiceImpl;
    @Autowired
    CommodityService commodityServiceImpl;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CommodityWantRepository commodityWantRepositoryImpl;

    @GetMapping("/get/all-res")
    @CrossOrigin
    public R<Object> getAllRes() {
        try{
            // 通过Redis获取UserId;
//            String userId = userServiceImpl.getTokenUser().getId();
//            // 获取推荐Post列表
//            List<Post> posts = postRepositoryImpl.getRecPostList();
//            List<PostVO> postVOList = getPostVOList(posts);
//            List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
//            Map<String, Object> map = new HashMap<>();
//            map.put("wall", postVOList);
//            map.put("market", recCommodityService);
//            redis查找
            Map<String,Object> rmap= (Map<String, Object>) redisTemplate.opsForValue().get("recommendedwam");
            if (rmap==null||rmap.size()==0){
                // 获取推荐Post列表
                List<Post> posts = postRepositoryImpl.getRecPostList();
                List<PostVO> postVOList = getPostVOList(posts);
                List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
                //
                String wantuserId = userServiceImpl.getTokenUser().getId();
                for (int i=0;i<recCommodityService.size();i++){
                    //是否想要
                    recCommodityService.get(i).setUserId(wantuserId);
                    Integer want=commodityWantRepositoryImpl.isLike(wantuserId,recCommodityService.get(i).getId());
                    if (want==null || want==0){
                        recCommodityService.get(i).setIsWant(false);
                    }else {
                        recCommodityService.get(i).setIsWant(true);
                    }
                }
                for (int i=0;i<postVOList.size();i++){
                    postVOList.get(i).getPost().setUserId(wantuserId);
                }
                //
                Map<String, Object> map = new HashMap<>();
                map.put("wall", postVOList);
                map.put("market", recCommodityService);
                //将map存入reidis
//                redisTemplate.opsForValue().set("recwall",postVOList);
//                redisTemplate.opsForValue().set("recmarket",recCommodityService);
                return R.success(map);
            }else {
                return R.success(rmap);
            }
        }catch (Exception e){
            return R.error();
        }
    }
    private List<PostVO> getPostVOList(List<Post> posts){
        // redis获取当前用户id
        String userId = userServiceImpl.getTokenUser().getId();
        List<PostVO> listVO = new ArrayList<>();
        for(Post post : posts){
            // 更新点赞数量
            post.setLikeCount(postLikeRepositoryImpl.getLikeCount(post.getId()));
            // 获取发行人
            User issuer = userServiceImpl.getById(post.getUserId());
            // 数据注入Post
            PostVO vo = new PostVO();
            vo.setPost(post);
            vo.setUserHead(issuer.getHeadaddress());
            vo.setUserNickname(issuer.getNickname());
            vo.setUserIsCollect(postCollectServiceImpl.isCollect(userId, post.getId()));
            // 判断当前登录用户是否点赞帖子
            Integer like = postLikeRepositoryImpl.isLike(userId, post.getId());
            // null: 从未点赞过, 1: 点赞状态, 0: 点赞过又取消状态
            if(like == null || like == 0) {
                vo.setUserIsLike(false);
            } else {
                vo.setUserIsLike(true);
            }
            // 数据注入图片列表
            List<String> images = postRepositoryImpl.getFileListByPostId(post.getId());
            vo.setImages(images);
            // 将结果插入到返回列表中
            listVO.add(vo);
        }
        return listVO;
    }
}
