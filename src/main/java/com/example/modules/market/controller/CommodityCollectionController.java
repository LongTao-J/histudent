package com.example.modules.market.controller;

import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.CommodityCollectionService;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/collection")
public class CommodityCollectionController {

    @Autowired
    CommodityCollectionService commodityCollectionServiceImpl;
    @Autowired
    CommodityWantRepository commodityWantRepositoryImpl;
    @Autowired
    UserService userServiceImpl;

    //点击收藏
    @PutMapping("/addCollection/{commodityId}")
    @CrossOrigin
    public R<String> addCollectionConr(@PathVariable("commodityId") String commodityId){
        try {
            boolean b = commodityCollectionServiceImpl.addCollectionSer(commodityId);
            if (b==true){
                return R.success("收藏成功");
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //取消收藏
    @PutMapping("/cancleCollection/{commodityId}")
    @CrossOrigin
    public R<String> cancleCollectionConr(@PathVariable("commodityId") String commodityId){
        try {
            boolean b = commodityCollectionServiceImpl.cancleCollectionSer(commodityId);
            if (b==true){
                return R.success("取消收藏成功");
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //查看我的收藏
    @GetMapping("/getAll")
    @CrossOrigin
    public R<List<CommodityVO>> getAll(){
        try {
            List<CommodityVO> allSer = commodityCollectionServiceImpl.getAllSer();
            for (int i=0;i<allSer.size();i++){
            //是否想要
            String wantuserId = userServiceImpl.getTokenUser().getId();
            Integer want=commodityWantRepositoryImpl.isLike(wantuserId,allSer.get(i).getId());
            if (want==null || want==0){
                allSer.get(i).setIsWant(false);
            }else {
                allSer.get(i).setIsWant(true);
            }

            }
            return R.success(allSer,"成功",200);
        }catch (Exception e){
            return R.error();
        }
    }


}
