package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.modules.market.entity.dto.WantLikeCountDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.market.mapper.CommodityWantMapper;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.CommodityWantService;
import com.example.modules.market.service.RedisLtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityWantServiceImpl implements CommodityWantService {
    @Autowired
    CommodityWantMapper commodityWantMapper;
    @Autowired
    RedisLtService redisLtServiceImpl;
    @Autowired
    CommodityService commodityServiceImpl;

    @Override
    public Boolean save(CommodityWant commodityWant) {
        try{
            CommodityWant temp = getWantByUserIdAndCommodityId(commodityWant.getUserId(),commodityWant.getCommodityId());
            if(temp == null) commodityWantMapper.insert(commodityWant);
            else {
                QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
                wrapper.eq("commodity_id", commodityWant.getCommodityId());
                wrapper.eq("user_id", commodityWant.getUserId());
                commodityWantMapper.update(commodityWant, wrapper);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveList(List<CommodityWant> list) {
        try{
            for(CommodityWant commodityWant : list){
                CommodityWant temp = getWantByUserIdAndCommodityId(commodityWant.getUserId(),commodityWant.getCommodityId());
                if(temp == null) commodityWantMapper.insert(commodityWant);
                else {
                    QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
                    wrapper.eq("commodity_id", commodityWant.getCommodityId());
                    wrapper.eq("user_id", commodityWant.getUserId());
                    commodityWantMapper.update(commodityWant, wrapper);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CommodityWant> getLikeListByPostId(String commodityId, IPage<CommodityWant> page) {
//        QueryWrapper<PostLike> wrapper = new QueryWrapper<>();
//        wrapper.eq("commodity_id", commodityId);
//        List<CommodityWant> commodityWants = commodityWantMapper.selectPageVo(page, wrapper);
        return null;
    }

    //根据点赞人的userId查询点赞列表（即查询这个人都给那些帖子赞过）
    @Override
    public List<CommodityWant> getWantListByUserId(String userId, IPage<CommodityWant> page) {
        QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<CommodityWant> commodityWants = commodityWantMapper.selectPageVoLt(page, wrapper);
        return commodityWants;
    }

    @Override
    public CommodityWant getWantByUserIdAndCommodityId(String userId, String commodityId) {
        QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("commodity_id", commodityId);
        return commodityWantMapper.selectOne(wrapper);
    }

    @Override
    public void transLikedFromRedis0DB() {
        List<CommodityWant> list = redisLtServiceImpl.getLikedDataWithRemoveFromRedis();
        saveList(list);
    }

    @Override
    public void transLikedCountFromRedis0DB() {
        List<WantLikeCountDTO> list = redisLtServiceImpl.getLikedCountWithRemoveFromRedis();
        for(WantLikeCountDTO dto : list){
            Commodity commodity = commodityServiceImpl.getCommodityById(dto.getCommodity());
            commodity.setWant(dto.getCount());
            commodityServiceImpl.updateCommodityById(commodity);
        }
    }

    @Override
    public void deleteLikeDate(String commodityId) {
        QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", commodityId);
        commodityWantMapper.delete(wrapper);
    }
}
