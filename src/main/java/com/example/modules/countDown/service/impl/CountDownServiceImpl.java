package com.example.modules.countDown.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.countDown.entity.dto.CountDownDTO;
import com.example.modules.countDown.entity.po.CountDown;
import com.example.modules.countDown.entity.vo.CountDownIdVO;
import com.example.modules.countDown.mapper.CountDownMapper;
import com.example.modules.countDown.service.CountDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountDownServiceImpl extends ServiceImpl<CountDownMapper, CountDown> implements CountDownService {

    @Autowired
    CountDownMapper countDownMapper;

    @Override
    public boolean addCdSer(CountDownDTO countDownDTO,String userid) {
        try {
            CountDown countDown=new CountDown();
            countDown.setAddress(countDownDTO.getAddress());
            countDown.setContent(countDownDTO.getContent());
            countDown.setUserId(userid);
            countDown.setRemindtime(countDownDTO.getRemindtime());
            countDownMapper.insert(countDown);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean upCdSer(CountDownIdVO countDownIdVO) {
        try {
            CountDown countDown = countDownMapper.selectById(countDownIdVO.getId());
            countDown.setRemindtime(countDownIdVO.getRemindtime());
            countDown.setAddress(countDownIdVO.getAddress());
            countDown.setRemindtime(countDownIdVO.getRemindtime());
            countDownMapper.updateById(countDown);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<CountDown> getmyCd(String userid) {
        try {
            QueryWrapper<CountDown> wrapper=new QueryWrapper<>();
            wrapper.eq("user_id",userid);
            List<CountDown> countDowns = countDownMapper.selectList(wrapper);
            return countDowns;
        }catch (Exception e){
            return null;
        }
    }
}
