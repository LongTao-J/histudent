package com.example.modules.countDown.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.countDown.entity.po.CountDown;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountDownMapper extends BaseMapper<CountDown> {
}
