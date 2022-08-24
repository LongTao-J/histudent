package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;

import java.util.List;

public interface CommodityService extends IService<Commodity> {

     //上架商品
     boolean issueCommodity(Commodity commodity);

     //查询所有商品
     List<CommodityVO> getAllCommodityService();

     public Commodity getCommodityById(String id);

     public Boolean updateCommodityById(Commodity commodity);

}
