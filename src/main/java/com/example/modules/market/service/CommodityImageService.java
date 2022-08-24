package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.po.CommodityImage;

import java.util.List;

public interface CommodityImageService extends IService<CommodityImage> {

    //插入图片
    void insertImg(String commodityId,String imgurl);

    //根据id查图片
    List<String> getAllImgService(String commodityId);

}
