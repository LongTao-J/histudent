package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.po.CommodityImage;

import java.util.List;

public interface CommodityImageService extends IService<CommodityImage> {

    void insertImg(String commodityId,String imgurl);

    List<String> getAllImgService(String commodityId);
}
