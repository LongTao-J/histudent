package com.example.modules.market.repository;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.wall.entity.dto.PostDTO;

import java.util.List;

public interface CommodityImgRepository {

    /**
     * 添加一个图片为商品
     * @param url
     */
    void uploadReleasePostFile(String userId, String url);

    /**
     * 取消发布商品
     * @param userId
     */
    void unissuePost(String userId);

    /**
     * 发布商品
     * @param commodity
     */
    void issuePost(Commodity commodity);

    /**
     * 获取发布图片缓存列表
     * @param userId
     * @return
     */
    List<String> getReleasePostFileListCache(String userId);

}
