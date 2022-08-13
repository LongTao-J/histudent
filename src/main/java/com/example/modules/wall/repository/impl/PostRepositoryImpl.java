package com.example.modules.wall.repository.impl;

import com.example.modules.wall.entity.dto.PostDTO;
import com.example.modules.wall.entity.dto.PostFileDTO;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.entity.po.PostCollect;
import com.example.modules.wall.repository.PostRepository;
import com.example.modules.wall.service.PostCollectService;
import com.example.modules.wall.service.PostFileService;
import com.example.modules.wall.service.PostService;
import com.example.modules.wall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    PostService postServiceImpl;
    @Autowired
    PostFileService postFileServiceImpl;
    @Autowired
    RedisService redisServiceImpl;
    @Autowired
    PostCollectService postCollectServiceImpl;

    @Override
    public List<String> getFileListByPostId(String postId) {
        // 在缓存中查找
        List<String> list = redisServiceImpl.getPostAllFileCache(postId);
        // 如果找不到就从MySql中查找
        if(list.isEmpty()){
            PostFileDTO postFile = postFileServiceImpl.getPostFile(postId);
            list = postFile.getFiles();
        }
        return list;
    }

    @Override
    public List<Post> getRecPostList() {
        return postServiceImpl.getPostListRec();
    }

    @Override
    public void recPost(String postId) {
        Post post = postServiceImpl.getPostById(postId);
        post.setIsRec(true);
        postServiceImpl.updatePostById(post);
    }

    @Override
    public void unRecPost(String postId) {
        Post post = postServiceImpl.getPostById(postId);
        post.setIsRec(false);
        postServiceImpl.updatePostById(post);
    }

    @Override
    public Post getPost(String postId) {
        return postServiceImpl.getPostById(postId);
    }

    @Override
    public List<Post> getUserIssuedPostList(String userId) {
        return postServiceImpl.getPostListUserIssued(userId);
    }

    @Override
    public void uploadReleasePostFile(String userId, String url) {
        redisServiceImpl.addReleasePostFileFromRedis(userId, url);
    }


    @Override
    public void unissuePost(String userId) {
        redisServiceImpl.clearReleasePostFileFromRedis(userId);
    }

    @Override
    public void issuePost(PostDTO postDTO) {
        Post post = postDTO.getPost();
        String userId = post.getUserId();
        List<String> images = postDTO.getImages();
        String postId = postServiceImpl.createPost(post);
        for(String url : images){
            postFileServiceImpl.insert(postId, url);
        }
        redisServiceImpl.clearReleasePostFileFromRedis(userId);
    }

    @Override
    public List<String> getReleasePostFileListCache(String userId) {
        return redisServiceImpl.getReleasePostFileAllFromRedis(userId);
    }

    @Override
    public List<Post> getUserCollectPostList(String userId) {
        List<Post> posts = new ArrayList<>();
        List<PostCollect> postCollects = postCollectServiceImpl.getUserCollectPostList(userId);
        for(PostCollect collect : postCollects){
            String postId = collect.getPostId();
            Post post = postServiceImpl.getPostById(postId);
            posts.add(post);
        }
        return posts;
    }
}
