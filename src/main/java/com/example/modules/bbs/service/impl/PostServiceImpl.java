package com.example.modules.bbs.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.bbs.entity.po.Post;
import com.example.modules.bbs.mapper.PostMapper;
import com.example.modules.bbs.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;


    @Override
    public Post getPostById(String id) {
        return postMapper.selectById(id);
    }

    @Override
    public List<Post> getPostListRec() {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("is_rec", 1);
        List<Post> list = postMapper.selectList(wrapper);
        return list;
    }

    @Override
    public String createPost(Post post) {
        postMapper.insert(post);
        return post.getId();
    }

    @Override
    @Transactional
    public Boolean updatePostById(Post post) {
        try{
            postMapper.updateById(post);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
