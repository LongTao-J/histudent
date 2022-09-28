package com.example.modules.wall.controller;


import com.example.modules.user.pojo.dto.UserInfoLt;
import com.example.modules.user.service.UserService;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.entity.po.PostCollect;
import com.example.modules.wall.entity.vo.PostCollectVO;
import com.example.modules.wall.repository.PostRepository;
import com.example.modules.wall.service.PostCollectService;
import com.example.modules.wall.service.PostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wall/post-collect")
public class PostCollectController {
    @Autowired
    PostService postServiceImpl;
    @Autowired
    PostRepository postRepositoryImpl;
    @Autowired
    PostCollectService postCollectServiceImpl;
    @Autowired
    UserService userServiceImpl;

    @PutMapping("/put/collect/{post-id}")
    @CrossOrigin
    public R<Object> collect(@PathVariable("post-id")String postId){
        try{
            String userId = userServiceImpl.getTokenUser().getId();
            postCollectServiceImpl.addCollect(userId, postId);
            Post post = postServiceImpl.getPostById(postId);
            post.setCollectCount(post.getCollectCount() + 1);
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/uncollect/{post-id}")
    @CrossOrigin
    public R<Object> uncollect(@PathVariable("post-id")String postId){
        try{
            String userId = userServiceImpl.getTokenUser().getId();
            postCollectServiceImpl.deleteCollect(userId, postId);
            Post post = postServiceImpl.getPostById(postId);
            post.setCollectCount(post.getCollectCount() - 1);
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/collect-list")
    @CrossOrigin
    public R<List<PostCollectVO>> getCollectList(){
        String userId = userServiceImpl.getTokenUser().getId();
        List<PostCollectVO> postCollectVOList = new ArrayList<>();
        List<PostCollect> userCollectPostList = postCollectServiceImpl.getUserCollectPostList(userId);
        for(PostCollect postCollect : userCollectPostList){
            String postId = postCollect.getPostId();
            Post post = postServiceImpl.getPostById(postId);
            UserInfoLt user = userServiceImpl.getUserInfolt(post.getUserId());
            PostCollectVO postCollectVO = new PostCollectVO();
            postCollectVO.setNickname(user.getNickname());
            postCollectVO.setTitle(post.getTitle());
            postCollectVO.setContent(post.getContent());
            List<String> images = postRepositoryImpl.getFileListByPostId(post.getId());
            postCollectVO.setImage(images.get(0));
            postCollectVO.setGmtCreate(postCollect.getGmtCreate());
            postCollectVOList.add(postCollectVO);
        }
        return R.success(postCollectVOList);
    }
}

