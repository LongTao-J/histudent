package com.example.modules.market.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.wall.entity.po.PostLike;

import java.util.List;

public interface CommodityWantService {

    /**
     * 保存点赞记录
     * @param commodityWant
     * @return
     */
    Boolean save(CommodityWant commodityWant);

    /**
     * 批量保存或修改
     * @param list
     */
    Boolean saveList(List<CommodityWant> list);

    /**
     * 根据postId查询点赞列表（即查询都谁给这帖子点赞过）
     * @param commodityId 被点赞帖子的id
     * @param page
     * @return
     */
    List<CommodityWant> getLikeListByPostId(String commodityId, IPage<CommodityWant> page);

    /**
     * 根据点赞人的userId查询点赞列表（即查询这个人都给那些帖子赞过）
     * @param userId
     * @param page
     * @return
     */
    List<CommodityWant> getWantListByUserId(String userId, IPage<CommodityWant> page);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param userId
     * @param commodityId
     * @return
     */
    CommodityWant getWantByUserIdAndCommodityId(String userId, String commodityId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis0DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis0DB();

    /**
     * 删除帖子所以喜欢数据
     * @param commodityId
     */
    void deleteLikeDate(String commodityId);
}
