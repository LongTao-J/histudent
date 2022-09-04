package com.example.modules.countDown.controller;

import com.example.modules.countDown.entity.dto.CountDownDTO;
import com.example.modules.countDown.entity.po.CountDown;
import com.example.modules.countDown.entity.vo.CountDownIdVO;
import com.example.modules.countDown.service.CountDownService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countDown")
public class CountDownController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CountDownService countDownServiceImpl;

    //添加倒计时
    @PostMapping("/add")
    @CrossOrigin
    public R<CountDownDTO> addCountDown(@RequestBody CountDownDTO countDownDTO){
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userid=redis.get(Consts.REDIS_USER);
            boolean b = countDownServiceImpl.addCdSer(countDownDTO,userid);
            if (b==true){
                return R.success(countDownDTO,"成功",200);
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //删除倒计时
    @DeleteMapping("/delete/{countDownId}")
    @CrossOrigin
    public R<String> deleteCountDown(@PathVariable("countDownId") String countDownId){
        try {
            countDownServiceImpl.removeById(countDownId);
            return R.success(countDownId,"成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

    //修改我的倒计时
    @PutMapping("/update")
    @CrossOrigin
    public R<CountDown> updateCountDownId(@RequestBody CountDown countDownIdVO){
        try {
            boolean b = countDownServiceImpl.upCdSer(countDownIdVO);
            if (b==true){
                return R.success(countDownIdVO,"成功",200);
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //查询我的倒计时
    @GetMapping("/getMyCountDownId")
    @CrossOrigin
    public R<List<CountDownIdVO>> getMyCD(){
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userid=redis.get(Consts.REDIS_USER);
            List<CountDownIdVO> countDowns = countDownServiceImpl.getmyCd(userid);
            return R.success(countDowns,"成功",200);
        }catch (Exception e){
            return R.error();
        }
    }
}
