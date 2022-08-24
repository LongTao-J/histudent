package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityImage;
import com.example.modules.market.mapper.CommodityImageMapper;
import com.example.modules.market.service.CommodityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityImageServiceImpl extends ServiceImpl<CommodityImageMapper, CommodityImage> implements CommodityImageService {

    @Autowired
    CommodityImageMapper commodityImageMapperImpl;

    @Override
    public void insertImg(String commodityId, String imgurl) {
        CommodityImage commodityImage = new CommodityImage();
        commodityImage.setCommodityId(commodityId);
        commodityImage.setCommodityImg(imgurl);
        commodityImageMapperImpl.insert(commodityImage);
    }

    @Override
    public List<String> getAllImgService(String commodityId) {
        return commodityImageMapperImpl.getAllCommodityImg(commodityId);
    }


}
