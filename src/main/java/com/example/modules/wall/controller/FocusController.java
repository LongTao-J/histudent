package com.example.modules.wall.controller;

import com.example.modules.user.pojo.User;
import com.example.modules.user.utils.Consts;
import com.example.modules.wall.entity.vo.FocusVO;
import com.example.modules.wall.repository.FocusRepository;
import com.example.modules.wall.service.FocusService;
import com.example.modules.wall.service.FocusStateService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注表 前端控制器
 * @author mushan
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/api/wall/focus")
public class FocusController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    FocusService focusServiceImpl;
    @Autowired
    FocusRepository focusRepositoryImpl;
    @Autowired
    FocusStateService focusStateServiceImpl;

    @PutMapping("/put/focus/{user-id-to}")
    @CrossOrigin
    public R<Object> focus(@PathVariable("user-id-to")String userIdTo){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userIdFrom = redis.get(Consts.REDIS_USER);
            focusRepositoryImpl.focus(userIdFrom, userIdTo);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @DeleteMapping("/delete/unfocus/{user-id-to}")
    @CrossOrigin
    public R<Object> unfocus(@PathVariable("user-id-to")String userIdTo){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userIdFrom = redis.get(Consts.REDIS_USER);
            focusRepositoryImpl.unfocus(userIdFrom, userIdTo);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/focus-list/{user-id}")
    @CrossOrigin
    public R<Object> getFocusList(@PathVariable("user-id") String userId){
        try{
            List<User> users = focusServiceImpl.getFocusList(userId);
            List<FocusVO> voList = new ArrayList<>();
            for(User user : users){
                String avatar = user.getHeadaddress();
                String nickname = user.getNickname();
                FocusVO focusVO = new FocusVO();
                focusVO.setAvatar(avatar);
                focusVO.setNickname(nickname);
                focusVO.setFansCount(focusStateServiceImpl.getFansCount(user.getId()));
                voList.add(focusVO);
            }
            return R.success(voList);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/fans-list/{user-id}")
    @CrossOrigin
    public R<Object> getFansList(@PathVariable("user-id") String userId){
        try{
            List<User> users = focusServiceImpl.getFansList(userId);
            List<FocusVO> voList = new ArrayList<>();
            for(User user : users){
                String avatar = user.getHeadaddress();
                String nickname = user.getNickname();
                FocusVO focusVO = new FocusVO();
                focusVO.setAvatar(avatar);
                focusVO.setNickname(nickname);
                focusVO.setFansCount(focusStateServiceImpl.getFansCount(user.getId()));
                voList.add(focusVO);
            }
            return R.success(voList);
        }catch (Exception e){
            return R.error();
        }
    }



    @GetMapping("/get/fans-list/oneself")
    @CrossOrigin
    public R<Object> getFansListBySelf(){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            return getFansList(userId);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/focus-list/oneself")
    @CrossOrigin
    public R<Object> getFocusListBySelf(){
        try{
            // redis获取当前用户id
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            return getFocusList(userId);
        }catch (Exception e){
            return R.error();
        }
    }
}

