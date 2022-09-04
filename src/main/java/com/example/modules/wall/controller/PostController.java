package com.example.modules.wall.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.modules.user.pojo.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.modules.wall.entity.dto.PostFileFromViewDTO;
import com.example.modules.wall.entity.dto.IssuePostDTO;
import com.example.modules.wall.entity.dto.PostDTO;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.entity.vo.PostVO;
import com.example.modules.wall.repository.PostLikeRepository;
import com.example.modules.wall.repository.PostRepository;
import com.example.modules.wall.service.PostCollectService;
import com.example.modules.wall.service.PostCommentService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/post")
public class PostController {
    @Autowired
    PostRepository postRepositoryImpl;
    @Autowired
    PostLikeRepository postLikeRepositoryImpl;
    @Autowired
    UserService userServiceImpl;
    @Autowired
    PostCollectService postCollectServiceImpl;
    @Autowired
    PostCommentService postCommentServiceImpl;
    @Autowired
    RedisTemplate redisTemplate;

    @PutMapping("/put/upload-file")
    @CrossOrigin
    public R<Object> uploadImg(@RequestBody PostFileFromViewDTO postFileFromViewDTO){
        try{
            // 通过Redis获取UserId;
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            String url = postFileFromViewDTO.getUrl();
            if(url == null || url.equals("")) {
                throw new Exception();
            }
            postRepositoryImpl.uploadReleasePostFile(userId, postFileFromViewDTO.getUrl());
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/issue-post")
    @CrossOrigin
    public R<Object> issuePost(@RequestBody IssuePostDTO issuePostDTO){
        try{
            // 通过Redis获取UserId;
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            Post post = new Post();
            post.setTitle(issuePostDTO.getTitle());
            post.setContent(issuePostDTO.getContent());
            post.setUserId(userId);
            List<String> list = postRepositoryImpl.getReleasePostFileListCache(userId);
            PostDTO dto = new PostDTO();
            dto.setPost(post);
            dto.setImages(list);
            postRepositoryImpl.issuePost(dto);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/un-issue-post")
    @CrossOrigin
    public R<Object> unissuePost(){
        try{
            // 通过Redis获取UserId;
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            postRepositoryImpl.unissuePost(userId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/all-rec")
    @CrossOrigin
    public R<Object> getListRec(){
        try{
            // 通过Redis获取UserId;
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            // 获取推荐Post列表
            List<Post> posts = postRepositoryImpl.getRecPostList();
            List<PostVO> postVOList = getPostVOList(posts);
            return R.success(postVOList);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/rec/{post-id}")
    @CrossOrigin
    public R<Object> setRec(@PathVariable("post-id") String postId) {
        try{
            postRepositoryImpl.recPost(postId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/un-rec/{post-id}")
    @CrossOrigin
    public R<Object> unRec(@PathVariable("post-id") String postId) {
        try{
            postRepositoryImpl.unRecPost(postId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/post-list/user-issued/{user-id}")
    @CrossOrigin
    public R<Object> getUserIssuedPostList(@PathVariable("user-id") String userId){
        try{
            List<Post> posts = postRepositoryImpl.getUserIssuedPostList(userId);
            List<PostVO> postVOList = getPostVOList(posts);
            return R.success(postVOList);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/post-list/user-issued/oneself")
    @CrossOrigin
    public R<Object> getOneSelfIssuedPostList(){
        try{
            // 通过Redis获取UserId;
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            return getUserIssuedPostList(userId);
        }catch (Exception e){
            return R.error();
        }
    }


    @GetMapping("/get/post-list/user-collect")
    @CrossOrigin
    public R<Object> getUserAllCollect(){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            List<Post> posts = postRepositoryImpl.getUserCollectPostList(userId);
            List<PostVO> postVOList = getPostVOList(posts);
            return R.success(postVOList);
        }catch (Exception e){
            return R.error();
        }
    }

    @DeleteMapping("/delete/post/{post-id}")
    @CrossOrigin
    public R<Object> deletePost(@PathVariable("post-id")String postId){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            Post post = postRepositoryImpl.getPost(postId);
            if(!post.getUserId().equals(userId)){
                return R.error("没有权限删除", 403);
            }else{
                postRepositoryImpl.deletePost(postId);
                return R.success(null);
            }
        }catch (Exception e){
            return R.error();
        }
    }

    private PostVO getPostVO(Post post){
        // redis获取当前用户id
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        // 更新点赞数量
        post.setLikeCount(postLikeRepositoryImpl.getLikeCount(post.getId()));
        // 获取发行人
        User issuer = userServiceImpl.getById(post.getUserId());
        // 数据注入Post
        PostVO vo = new PostVO();
        vo.setPost(post);
        vo.setUserHead(issuer.getHeadaddress());
        vo.setUserNickname(issuer.getNickname());
        // 判断当前登录用户是否点赞帖子
        Integer like = postLikeRepositoryImpl.isLike(userId, post.getId());
        // null: 从未点赞过, 1: 点赞状态, 0: 点赞过又取消状态
        if(like == null || like == 0) {
            vo.setUserIsLike(false);
        } else {
            vo.setUserIsLike(true);
        }
        // 判断当前登录用户是否收藏帖子
        Boolean isCollect = postCollectServiceImpl.isCollect(userId, post.getId());
        // 1: 收藏, 0: 未收藏
        if(isCollect) {
            vo.setUserIsCollect(true);
        } else {
            vo.setUserIsCollect(false);
        }

        // 数据注入图片列表
        List<String> images = postRepositoryImpl.getFileListByPostId(post.getId());
        vo.setImages(images);
        return vo;
    }

    private List<PostVO> getPostVOList(List<Post> posts){
        // redis获取当前用户id
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
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
