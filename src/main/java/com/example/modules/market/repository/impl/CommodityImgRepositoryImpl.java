package com.example.modules.market.repository.impl;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.repository.CommodityImgRepository;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.RedisLtService;
import com.example.modules.wall.entity.dto.PostDTO;
import com.example.modules.wall.entity.po.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommodityImgRepositoryImpl implements CommodityImgRepository {

    @Autowired
    RedisLtService redisLtServiceImpl;

    @Autowired
    CommodityService commodityServiceImpl;

    @Override
    public void uploadReleasePostFile(String userId, String url) {
        redisLtServiceImpl.addCommodityImagetoRedis(userId, url);
    }

    @Override
    public void unissuePost(String userId) {
        redisLtServiceImpl.clearCommodityImage(userId);
    }

    @Override
    public void issuePost(Commodity commodity) {
        commodityServiceImpl.issueCommodity(commodity);
    }

    @Override
    public List<String> getReleasePostFileListCache(String userId) {

        return redisLtServiceImpl.getCommodityAllImgFromRedis(userId);
    }
}
