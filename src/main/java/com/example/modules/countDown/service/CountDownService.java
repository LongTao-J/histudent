package com.example.modules.countDown.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.countDown.entity.dto.CountDownDTO;
import com.example.modules.countDown.entity.po.CountDown;
import com.example.modules.countDown.entity.vo.CountDownIdVO;

import java.util.List;

public interface CountDownService extends IService<CountDown> {

    //新增倒计时
    boolean addCdSer(CountDownDTO countDownDTO,String userid);

    //修改倒计时
    boolean upCdSer(CountDown countDownIdVO);

    //查询我的倒计时
    List<CountDownIdVO> getmyCd(String userid);
}
