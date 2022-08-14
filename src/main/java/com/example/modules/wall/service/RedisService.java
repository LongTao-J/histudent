package com.example.modules.wall.service;



import com.example.modules.wall.entity.dto.PostLikedCountDTO;
import com.example.modules.wall.entity.po.PostLike;

import java.util.List;


public interface RedisService {
    /****************************************
     *************** 点赞相关 ****************
     *****************************************/

    /**
     * 点赞。将状态设为1
     * @param userId
     * @param postId
     */
    void savelikeFromRedis(String userId, String postId);

    /**
     * 取消点赞。将状态改变为0
     * @param userId
     * @param postId
     */
    void unlikeFromRedis(String userId, String postId);

    /**
     * 从Redis中删除一条点赞数据
     * @param userId
     * @param postId
     */
    void deleteLikedFromRedis(String userId, String postId);

    /**
     * 该帖子的点赞数加1
     * @param postId
     */
    void incrementLikedCount(String postId);

    /**
     * 该帖子的点赞数减1
     * @param postId
     */
    void decrementLikedCount(String postId);

    /**
     * 设置点赞数量
     * @param postId
     */
    void setLikeCountFromRedis(String postId, Integer value);

    /**
     * 获取是否点赞从redis
     * @param userId
     * @param postId
     * @return
     */
    Integer getIsLikeFromRedis(String userId, String postId);

    /**
     * 获取帖子被点赞数量从redis中
     * @param postId
     * @return
     */
    Integer getLikedCountFromRedisByPostId(String postId);

    /**
     * 获取Redis中存储的所有点赞数据并清除
     * @return
     */
    List<PostLike> getLikedDataWithRemoveFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量并清除
     * @return
     */
    List<PostLikedCountDTO> getLikedCountWithRemoveFromRedis();

    /**
     * 清空点赞数量缓存
     * @param postId
     */
    void clearPostLikeCountCache(String postId);

    /**
     * 情况帖子喜欢缓存
     * @param postId
     */
    void clearPostLikeCache(String postId);

    /****************************************
     *************** 帖子文件缓存 ****************
     *****************************************/

    /**
     * 左边插入一个图像缓存
     * @param postId
     * @param url
     */
    void lpushPostFileCache(String postId, String url);

    /**
     * 右插入一个图像缓存
     * @param postId
     * @param url
     */
    void rpushPostFileCache(String postId, String url);

    /**
     * 左删除一个图像缓存
     * @param postId
     */
    void lpopPostFileCache(String postId);

    /**
     * 右删除一个图像缓存
     * @param postId
     */
    void rpopPostFileCache(String postId);

    /**
     * 获取当前缓存数据个数
     * @param postId
     * @return
     */
    Long getPostFileCacheSize(String postId);

    /**
     * 通过下标获取缓存中对应图像URL
     * @param postId
     * @param index
     * @return
     */
    String getPostFileCacheByIndex(String postId, Long index);

    /**
     * 获取图片缓存列表通过范围[start, end], 可用负数类似python
     * @param postId
     * @param start
     * @param end
     * @return
     */
    List<String> getPostFileCacheListByRange(String postId, Long start, Long end);

    /**
     * 获取帖子所有图片缓存
     * @param postId
     * @return
     */
    List<String> getPostAllFileCache(String postId);

    /**
     * 清除帖子图片缓存
     * @param postId
     */
    void clearPostFileCache(String postId);



    /****************************************
     *************** 发布文件缓存 ****************
     *****************************************/

    /**
     * 添加文件缓存至发布文件缓存列表
     * @param userId
     * @param url
     */
    void addReleasePostFileFromRedis(String userId, String url);

    /**
     * 获取帖子发布所有文件缓存
     * @param userId
     */
    List<String> getReleasePostFileAllFromRedis(String userId);

    /**
     * 获取帖子发布文件缓存通过下标
     * @param userId
     */
    String getReleasePostFileByIndexFromRedis(String userId, Long index);

    /**
     * 获取帖子发布文件列表尺寸
     * @param userId
     */
    Long getReleasePostFileListSizeFromRedis(String userId);

    /**
     * 清空帖子发布文件缓存
     * @param userId
     */
    void clearReleasePostFileFromRedis(String userId);

    /**
     * 删除帖子所以Redis数据
     * @param postId
     */
    void deleteRedisPostDate(String postId);
}
