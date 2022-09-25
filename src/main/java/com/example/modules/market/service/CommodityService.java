package com.example.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;

import java.util.List;

public interface CommodityService extends IService<Commodity> {

     //上架商品
     boolean issueCommodity(CommodityDTO commodityDTO);

     //查询所有商品
     List<CommodityVO> getAllCommodityService();

     //查询我发布的商品
     List<CommodityVO> getMyCommodityService();

     //取消发布商品
     boolean cancleImg();

     //根据id查商品
     public Commodity getCommodityById(String id);

     //更新商品
     public Boolean updateCommodityById(Commodity commodity);

     //查询我想要的商品
     List<CommodityVO> getMyWantCommodityServie(String userid);

     //根据想要商品id查商品
     CommodityVO getMyWantCommodityByCidSer(String commodityId);

     //查询推荐商品
     List<CommodityVO> getRecCommodityService();

     //根据介绍（introduce）查询某个商品
     List<CommodityVO> getCommodityOne(String text);

     //随机推荐商品
     List<CommodityVO> getRandonCommodity();
}
