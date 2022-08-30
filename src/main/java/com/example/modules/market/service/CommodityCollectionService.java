package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.po.CommodityCollection;
import com.example.modules.market.entity.vo.CommodityVO;

import java.util.List;

public interface CommodityCollectionService extends IService<CommodityCollection> {
    //点击收藏
    boolean addCollectionSer(String commodityId);

    //取消收藏
    boolean cancleCollectionSer(String commodity);

    //查看我的收藏
    List<CommodityVO> getAllSer();

    //查询商品收藏的个数
    Integer getCommodityCollectionCount(String commodityId);

}
