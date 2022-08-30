package com.example.modules.market.repository;

import com.example.modules.market.entity.vo.CommodityVO;

import java.util.List;

public interface CommodityWantRepository {
    /**
     * 点赞
     * @param userId
     * @param commodityId
     */
    void savelike(String userId, String commodityId);

    /**
     * 取消点赞
     * @param userId
     * @param commodityId
     */
    void unlike(String userId, String commodityId);

    /**
     * 是否点赞
     * @param userId
     * @param commodityId
     * @return
     */
    Integer isLike(String userId, String commodityId);

    /**
     * 获取点赞数量
     * @param commodityId
     * @return
     */
    Integer getLikeCount(String commodityId);

    //查询我想要的商品
    List<CommodityVO> getMyWantCommodity(String userId);
}
