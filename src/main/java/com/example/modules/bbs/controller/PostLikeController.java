package com.example.modules.bbs.controller;

import com.example.modules.bbs.repository.PostLikeRepository;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/postlike")
public class PostLikeController {
    @Autowired
    private PostLikeRepository postLikeRepositoryImpl;

    @PutMapping("/put/like/{post-id}/{user-id}")
    @CrossOrigin
    public R<Object> like(@PathVariable("post-id") String postId, @PathVariable("user-id") String userId){
        try{
            postLikeRepositoryImpl.savelike(userId, postId);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @PutMapping("/put/unlike/{post-id}/{user-id}")
    @CrossOrigin
    public R<Object> unlike(@PathVariable("post-id") String postId, @PathVariable("user-id") String userId){
        try{
            postLikeRepositoryImpl.unlike(userId, postId);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/get/islike/{post-id}/{user-id}")
    @CrossOrigin
    public R<Object> getIsLike(@PathVariable("post-id") String postId, @PathVariable("user-id") String userId){
        try{
            Integer like = postLikeRepositoryImpl.isLike(userId, postId);
            return R.success(like);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/get/like-count/{post-id}")
    @CrossOrigin
    public R<Object> getIsLike(@PathVariable("post-id") String postId){
        try{
            Integer count = postLikeRepositoryImpl.getLikeCount(postId);
            return R.success(count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
