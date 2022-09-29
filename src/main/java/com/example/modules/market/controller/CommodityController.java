package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.service.UserService;
import com.example.utils.R;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/market/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityServiceImpl;
    @Autowired
    CommodityImageService commodityImageServiceImpl;
    @Autowired
    CommodityWantRepository commodityWantRepositoryImpl;
    @Autowired
    UserService userServiceImpl;

    //发布商品
    @PostMapping("/issue")
    @CrossOrigin
    public R<String> addCommodity(@RequestBody CommodityDTO commodityDTO){
        try {
            commodityServiceImpl.issueCommodity(commodityDTO);
            return R.success("发布商品成功");
        }catch (Exception e){
            return R.error();
        }
    }

    //取消发布商品
    @PutMapping("/cancle")
    @CrossOrigin
    public R<String> cancleCommodity(){
        commodityServiceImpl.cancleImg();
        return R.success("取消成功");
    }

    //查询所有商品
    @GetMapping("/getAll")
    @CrossOrigin
    public R<List<CommodityVO>> getAllCommodity(){
        try {
            List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
            for (int i=0;i<voList.size();i++){
                //是否想要
                String wantuserId = userServiceImpl.getTokenUser().getId();
                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,voList.get(i).getId());
                if (want==null || want==0){
                    voList.get(i).setIsWant(false);
                }else {
                    voList.get(i).setIsWant(true);
                }
            }
            return R.success(voList,"查询所有商品成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

    //根据介绍（introduce）查询某个商品
    @GetMapping("/getCommodityOne/{text}")
    @CrossOrigin
    public R<List<CommodityVO>> getCommodityOne(@PathVariable("text") String text){
        try{
            List<CommodityVO> list=new ArrayList<>();
            list=commodityServiceImpl.getCommodityOne(text);
            for (int i=0;i<list.size();i++){
                //是否想要
                String wantuserId = userServiceImpl.getTokenUser().getId();
                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,list.get(i).getId());
                if (want==null || want==0){
                    list.get(i).setIsWant(false);
                }else {
                    list.get(i).setIsWant(true);
                }
            }
            return R.success(list);
        }catch (Exception e){
            return R.error();
        }
    }

    //查询我发布的商品
    @GetMapping("/getMyCommodity")
    @CrossOrigin
    public R<List<CommodityVO>> getCommodityByUser(){
        try {
            List<CommodityVO> myCommodityService = commodityServiceImpl.getMyCommodityService();
            for (int i=0;i<myCommodityService.size();i++){
                //是否想要
                String wantuserId = userServiceImpl.getTokenUser().getId();
                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,myCommodityService.get(i).getId());
                if (want==null || want==0){
                    myCommodityService.get(i).setIsWant(false);
                }else {
                    myCommodityService.get(i).setIsWant(true);
                }
            }
            return R.success(myCommodityService);
        }catch (Exception e){
            return R.error();
        }
    }

    //查看推荐的商品
    @GetMapping("/getRecCommodity")
    @CrossOrigin(origins = "http://localhost:8081")
    public R<List<CommodityVO>> getRecCommodity(){
        try {
            List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
            for (int i=0;i<recCommodityService.size();i++){
                //是否想要
                String wantuserId = userServiceImpl.getTokenUser().getId();
                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,recCommodityService.get(i).getId());
                if (want==null || want==0){
                    recCommodityService.get(i).setIsWant(false);
                }else {
                    recCommodityService.get(i).setIsWant(true);
                }
            }
            return R.success(recCommodityService);
        }catch (Exception e){
            return R.error();
        }
    }

    //随机推荐商品
    @GetMapping("/getRandonCommodity")
    @CrossOrigin
    public R<List<CommodityVO>> getRandonCommodity(){
        try {
            List<CommodityVO> recCommodityService = commodityServiceImpl.getRandonCommodity();
            for (int i=0;i<recCommodityService.size();i++){
                //是否想要
                String wantuserId = userServiceImpl.getTokenUser().getId();
                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,recCommodityService.get(i).getId());
                if (want==null || want==0){
                    recCommodityService.get(i).setIsWant(false);
                }else {
                    recCommodityService.get(i).setIsWant(true);
                }
            }
            return R.success(recCommodityService);
        }catch (Exception e){
            return R.error();
        }
    }
}
