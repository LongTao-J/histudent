package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.CommodityCollectionService;
import com.example.modules.market.service.CommodityCommentService;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    @Autowired
    RedisLtServiceImpl redisLtServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    CommodityCommentService commodityCommentServiceImpl;

    @Autowired
    CommodityCollectionService commodityCollectionServiceImpl;


    @Override
    public boolean issueCommodity(CommodityDTO commodityDTO) {
        try {
            String userId = userServiceImpl.getTokenUser().getId();

            Commodity commodity=new Commodity();
            commodity.setPrice(commodityDTO.getPrice());
            commodity.setIntroduce(commodityDTO.getIntroduce());
            commodity.setUserId(userId);
            commodityMapper.insert(commodity);
            List<String> imgs = redisLtServiceImpl.getCommodityAllImgFromRedis(userId);
            for (String img:imgs){
                commodityImageServiceImpl.insertImg(commodity.getId(),img);
            }
            redisLtServiceImpl.clearCommodityImage(commodity.getUserId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //查询所有商品
    @Override
    public List<CommodityVO> getAllCommodityService() {
        List<CommodityVO> commodityVOList=new ArrayList<>();
        commodityVOList=commodityMapper.getAllCommodityVo();
        for (int i=0;i<commodityVOList.size();i++){
            commodityVOList.get(i).setIsCollection(commodityCollectionServiceImpl.getIsCollectionByCommodidtyId(commodityVOList.get(i).getId())  );
            List<String> allImg=new ArrayList<>();
            allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
            if (allImg==null){
                commodityVOList.get(i).setAllImg(null);
                commodityVOList.get(i).setTotalImage(null);
            }else {
                commodityVOList.get(i).setAllImg(allImg);
                System.out.println("=========== "+allImg);
            }
            Integer commentCount = commodityCommentServiceImpl.getCommentCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCommentCount(commentCount);
            Integer likedCountFromRedisByPostId = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityVOList.get(i).getId());
            if (likedCountFromRedisByPostId==null || likedCountFromRedisByPostId==0){

            }else {
                commodityVOList.get(i).setWant(likedCountFromRedisByPostId);
            }

            //收藏数量
            Integer commodityCollectionCount = commodityCollectionServiceImpl.getCommodityCollectionCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCollectionCount(commodityCollectionCount);

            //是否想要
//            String wantuserId = userServiceImpl.getTokenUser().getId();
//            Integer want=commodityWantRepositoryImpl.isLike(wantuserId,commodityVOList.get(i).getId());
//            commodityVOList.get(i).setIsWant(want);

        }

        return commodityVOList;
    }

    //查询我发布的商品
    @Override
    public List<CommodityVO> getMyCommodityService() {
        String userId = userServiceImpl.getTokenUser().getId();

        List<CommodityVO> commodityVOList=new ArrayList<>();
        commodityVOList=commodityMapper.getMyCommodityVo(userId);

        try {
            for (int i=0;i<commodityVOList.size();i++){
                commodityVOList.get(i).setIsCollection(commodityCollectionServiceImpl.getIsCollectionByCommodidtyId(commodityVOList.get(i).getId())  );
                List<String> allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
                commodityVOList.get(i).setAllImg(allImg);
                commodityVOList.get(i).setTotalImage(allImg.get(0));
                Integer commentCount = commodityCommentServiceImpl.getCommentCount(commodityVOList.get(i).getId());
                commodityVOList.get(i).setCommentCount(commentCount);
                Integer likedCountFromRedisByPostId = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityVOList.get(i).getId());
                if (likedCountFromRedisByPostId==null || likedCountFromRedisByPostId==0){

                }
                else {
                    commodityVOList.get(i).setWant(likedCountFromRedisByPostId);
                }
                //收藏数量
                Integer commodityCollectionCount = commodityCollectionServiceImpl.getCommodityCollectionCount(commodityVOList.get(i).getId());
                commodityVOList.get(i).setCollectionCount(commodityCollectionCount);

                //是否想要
//                String wantuserId = userServiceImpl.getTokenUser().getId();
//                Integer want=commodityWantRepositoryImpl.isLike(wantuserId,commodityVOList.get(i).getId());
//                commodityVOList.get(i).setIsWant(want);
            }
            return commodityVOList;
        }catch (Exception e){
            return commodityVOList;
        }finally {
            return commodityVOList;
        }

    }

    @Override
    public boolean cancleImg() {
        String userId = userServiceImpl.getTokenUser().getId();

        redisLtServiceImpl.clearCommodityImage(userId);
        return true;
    }

    @Override
    public Commodity getCommodityById(String id) {
        return commodityMapper.selectById(id);
    }


    @Override
    public Boolean updateCommodityById(Commodity commodity) {
        try{
            commodityMapper.updateById(commodity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CommodityVO> getMyWantCommodityServie(String userid) {
        return commodityMapper.getMyWantCommodity(userid);
    }

    @Override
    public CommodityVO getMyWantCommodityByCidSer(String commodityId) {
        return commodityMapper.getMyWantCommodityVoByCId(commodityId);
    }

    @Override
    public List<CommodityVO> getRecCommodityService() {
        List<CommodityVO> commodityVOList=new ArrayList<>();
        commodityVOList=commodityMapper.getRecCommodityVo();
        for (int i=0;i<commodityVOList.size();i++){
            commodityVOList.get(i).setIsCollection(commodityCollectionServiceImpl.getIsCollectionByCommodidtyId(commodityVOList.get(i).getId())  );
            List<String> allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
            commodityVOList.get(i).setAllImg(allImg);
            commodityVOList.get(i).setTotalImage(allImg.get(0));
            Integer commentCount = commodityCommentServiceImpl.getCommentCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCommentCount(commentCount);
            Integer likedCountFromRedisByPostId = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityVOList.get(i).getId());
            if (likedCountFromRedisByPostId==null || likedCountFromRedisByPostId==0){

            }else {
                commodityVOList.get(i).setWant(likedCountFromRedisByPostId);
            }

            //收藏数量
            Integer commodityCollectionCount = commodityCollectionServiceImpl.getCommodityCollectionCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCollectionCount(commodityCollectionCount);

            //是否想要
//            String wantuserId = userServiceImpl.getTokenUser().getId();
//            Integer want=commodityWantRepositoryImpl.isLike(wantuserId,commodityVOList.get(i).getId());
//            commodityVOList.get(i).setIsWant(want);
        }

        return commodityVOList;
    }

    //根据介绍（introduce）查询某个商品
    @Override
    public List<CommodityVO> getCommodityOne(String text) {
        List<CommodityVO> commodityVOList=new ArrayList<>();
        //introduce查
        commodityVOList=commodityMapper.getCommodityOne("%"+text+"%");
        for (int i=0;i<commodityVOList.size();i++){
            commodityVOList.get(i).setIsCollection(commodityCollectionServiceImpl.getIsCollectionByCommodidtyId(commodityVOList.get(i).getId())  );
            List<String> allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
            commodityVOList.get(i).setAllImg(allImg);
            commodityVOList.get(i).setTotalImage(allImg.get(0));
            Integer commentCount = commodityCommentServiceImpl.getCommentCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCommentCount(commentCount);
            Integer likedCountFromRedisByPostId = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityVOList.get(i).getId());
            if (likedCountFromRedisByPostId==null || likedCountFromRedisByPostId==0){

            }else {
                commodityVOList.get(i).setWant(likedCountFromRedisByPostId);
            }

            //收藏数量
            Integer commodityCollectionCount = commodityCollectionServiceImpl.getCommodityCollectionCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCollectionCount(commodityCollectionCount);

            //是否想要
//            String wantuserId = userServiceImpl.getTokenUser().getId();
//            Integer want=commodityWantRepositoryImpl.isLike(wantuserId,commodityVOList.get(i).getId());
//            commodityVOList.get(i).setIsWant(want);

        }

        return commodityVOList;
    }

    //随机推荐商品
    @Override
    public List<CommodityVO> getRandonCommodity() {
        List<CommodityVO> commodityVOList2=new ArrayList<>();
        commodityVOList2=commodityMapper.getAllCommodityVo();
        List<CommodityVO> commodityVOList=new ArrayList<>();
        for (int i=0;i<commodityVOList2.size();i++){
            commodityVOList.add(commodityVOList2.get(i));
            i++;
        }
        for (int i=0;i<commodityVOList.size();i++){
            commodityVOList.get(i).setIsCollection(commodityCollectionServiceImpl.getIsCollectionByCommodidtyId(commodityVOList.get(i).getId())  );
            List<String> allImg=new ArrayList<>();
            allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
            commodityVOList.get(i).setAllImg(allImg);
            commodityVOList.get(i).setTotalImage(allImg.get(0));
            Integer commentCount = commodityCommentServiceImpl.getCommentCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCommentCount(commentCount);
            Integer likedCountFromRedisByPostId = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityVOList.get(i).getId());
            if (likedCountFromRedisByPostId==null || likedCountFromRedisByPostId==0){

            }else {
                commodityVOList.get(i).setWant(likedCountFromRedisByPostId);
            }

            //收藏数量
            Integer commodityCollectionCount = commodityCollectionServiceImpl.getCommodityCollectionCount(commodityVOList.get(i).getId());
            commodityVOList.get(i).setCollectionCount(commodityCollectionCount);

            //是否想要
//            String wantuserId = userServiceImpl.getTokenUser().getId();
//            Integer want=commodityWantRepositoryImpl.isLike(wantuserId,commodityVOList.get(i).getId());
//            commodityVOList.get(i).setIsWant(want);
        }

        return commodityVOList;
    }

    @Override
    public String getUserIdByCommodityId(String commodityId) {
        Commodity commodity = commodityMapper.selectById(commodityId);
        return commodity.getUserId();
    }

}
