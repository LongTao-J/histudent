package com.example.modules.epidemic.controller;


import com.example.modules.epidemic.entity.dto.AddNucleicFromViewDTO;
import com.example.modules.epidemic.entity.po.Nucleic;
import com.example.modules.epidemic.service.NucleicService;
import com.example.modules.user.pojo.StuInfo;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生核酸表 前端控制器
 * @author mushan
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/api/epidemic/nucleic")
public class NucleicController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userServiceImpl;
    @Autowired
    NucleicService nucleicServiceImpl;

    @PutMapping("/put/do-nucleic")
    @CrossOrigin
    public R<Object> addNucleic(@RequestBody AddNucleicFromViewDTO dto){
        try{
            nucleicServiceImpl.addNucleic(dto.getSchId(), dto.getStuNum(), dto.getState());
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/nucleic-7day")
    @CrossOrigin
    public R<Object> getSevenDayNucleicList(){
        // redis获取当前用户id
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        StuInfo stuInfo = userServiceImpl.getStuInfo(userId);
        String schId = stuInfo.getSchId();
        String stuNum = stuInfo.getStuNum();
        List<Nucleic> list = nucleicServiceImpl.getNucleicList(schId, stuNum);
        return R.success(list);
    }
}

