package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.po.CommodityImage;
import com.example.modules.market.mapper.CommodityImageMapper;
import com.example.modules.market.service.CommodityImageService;
import org.springframework.stereotype.Service;

@Service
public class CommodityImageServiceImpl extends ServiceImpl<CommodityImageMapper, CommodityImage> implements CommodityImageService {
}
