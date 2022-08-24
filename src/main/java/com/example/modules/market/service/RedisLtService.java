package com.example.modules.market.service;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.dto.WantLikeCountDTO;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.wall.entity.dto.PostLikedCountDTO;

import java.util.List;

public interface RedisLtService {

    /**
     * 想要。状态为1
     * @param UserId
     * @param commodityId
     */
    void saveLikedRedis(String UserId, String commodityId);

    /**
     * 取消想要。将状态改变为0
     * @param UserId
     * @param commodityId
     */
    void unlikeFromRedis(String UserId, String commodityId);

    /**
     * 从Redis中删除一条想要数据
     * @param UserId
     * @param commodityId
     */
    void deleteLikedFromRedis(String UserId, String commodityId);

    /**
     * 该用户的想要数加1
     * @param UserId
     */
    void incrementLikedCount(String UserId);

    /**
     * 该用户的想要数减1
     * @param UserId
     */
    void decrementLikedCount(String UserId);

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
    List<CommodityWant> getLikedDataWithRemoveFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量并清除
     * @return
     */
    List<WantLikeCountDTO> getLikedCountWithRemoveFromRedis();

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

    //图片================================

    //将图片存到redis
    void addCommodityImagetoRedis(String userId, String url);

    //清除redis图片缓存
    void clearCommodityImage(String userid);

    //获取所有图片
    List<String> getCommodityAllImgFromRedis(String userId);

    //发布商品
//    void issueCommodity(CommodityDTO commodityDTO);
}
