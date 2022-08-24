package com.example.modules.market.controller;

import com.example.modules.market.service.CommodityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/market/image")
public class CommodityImageController {

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    //上传商品的照片

}
