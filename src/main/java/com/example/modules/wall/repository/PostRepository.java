package com.example.modules.wall.repository;

import com.example.modules.wall.entity.dto.PostDTO;
import com.example.modules.wall.entity.po.Post;

import java.util.List;

public interface PostRepository {

    /**
     * 获取帖子图像列表
     * @param postId
     * @return
     */
    List<String> getFileListByPostId(String postId);

    /**
     * 获取帖子推荐列表
     * @return
     */
    List<Post> getRecPostList();

    /**
     * 将帖子设置为推荐
     * @param postId
     */
    void recPost(String postId);

    /**
     * 将帖子设置为推荐
     * @param postId
     */
    void unRecPost(String postId);

    /**
     * 获取帖子
     * @param postId
     * @return
     */
    Post getPost(String postId);

    /**
     * 获取用户发布的所有帖子
     * @param userId
     * @return
     */
    List<Post> getUserIssuedPostList(String userId);
    //---------------------------------------------------------

    /**
     * 添加一个图片为帖子
     * @param url
     */
    void uploadReleasePostFile(String userId, String url);

    /**
     * 取消发布帖子
     * @param userId
     */
    void unissuePost(String userId);

    /**
     * 发布帖子
     * @param postDTO
     */
    void issuePost(PostDTO postDTO);

    /**
     * 获取发布文件缓存列表
     * @param userId
     * @return
     */
    List<String> getReleasePostFileListCache(String userId);


}
