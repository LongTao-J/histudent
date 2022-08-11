package com.example.modules.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.modules.bbs.entity.dto.PostLikedCountDTO;
import com.example.modules.bbs.entity.po.Post;
import com.example.modules.bbs.entity.po.PostLike;
import com.example.modules.bbs.mapper.PostLikeMapper;
import com.example.modules.bbs.service.PostLikeService;
import com.example.modules.bbs.service.PostService;
import com.example.modules.bbs.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {
    @Autowired
    PostLikeMapper postLikeMapper;
    @Autowired
    RedisService redisServiceImpl;
    @Autowired
    PostService postServiceImpl;

    @Override
    @Transactional
    public Boolean save(PostLike postLike) {
        try{
            PostLike temp = getLikeByUserIdAndPostId(postLike.getUserId(), postLike.getPostId());
            if(temp == null) postLikeMapper.insert(postLike);
            else {
                QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
                wrapper.eq("post_id", postLike.getPostId());
                wrapper.eq("user_id", postLike.getUserId());
                postLikeMapper.update(postLike, wrapper);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveList(List<PostLike> list) {
        try{
            for(PostLike like : list){
                PostLike temp = getLikeByUserIdAndPostId(like.getUserId(), like.getPostId());
                if(temp == null) postLikeMapper.insert(like);
                else {
                    QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
                    wrapper.eq("post_id", like.getPostId());
                    wrapper.eq("user_id", like.getUserId());
                    postLikeMapper.update(like, wrapper);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PostLike> getLikeListByPostId(String postId, IPage<PostLike> page) {
        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        List<PostLike> postLikes = postLikeMapper.selectPageVo(page, wrapper);
        return postLikes;
    }

    @Override
    public List<PostLike> getLikeListByUserId(String userId, IPage<PostLike> page) {
        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<PostLike> postLikes = postLikeMapper.selectPageVo(page, wrapper);
        return postLikes;
    }

    @Override
    public PostLike getLikeByUserIdAndPostId(String userId, String postId) {
        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("post_id", postId);
        return postLikeMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public void transLikedFromRedis0DB() {
        List<PostLike> list = redisServiceImpl.getLikedDataWithRemoveFromRedis();
        saveList(list);
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis0DB() {
        List<PostLikedCountDTO> list = redisServiceImpl.getLikedCountWithRemoveFromRedis();
        for(PostLikedCountDTO dto : list){
            Post post = postServiceImpl.getPostById(dto.getPostId());
            post.setLikeCount(dto.getCount());
            postServiceImpl.updatePostById(post);
        }
    }
}
