package com.example.modules.wall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.wall.entity.po.FocusState;
import com.example.modules.wall.mapper.FocusStateMapper;
import com.example.modules.wall.service.FocusStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 关注状态表 服务实现类
 * @author mushan
 * @since 2022-08-22
 */
@Service
public class FocusStateServiceImpl extends ServiceImpl<FocusStateMapper, FocusState> implements FocusStateService {
    @Autowired
    FocusStateMapper focusStateMapper;

    @Override
    public void increbyFan(String userIdTo) {
        FocusState focusState = new FocusState();
        focusState.setUserId(userIdTo);
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userIdTo);
        FocusState temp = focusStateMapper.selectOne(wrapper);
        if(temp == null){
            focusState.setFansCount(1);
            focusState.setFocusCount(0);
            focusStateMapper.insert(focusState);
        }else{
            focusState.setFansCount(temp.getFansCount() + 1);
            focusState.setFocusCount(temp.getFocusCount());
            focusStateMapper.updateById(focusState);
        }
    }

    @Override
    public void increbyFocus(String userIdFrom) {
        FocusState focusState = new FocusState();
        focusState.setUserId(userIdFrom);
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userIdFrom);
        FocusState temp = focusStateMapper.selectOne(wrapper);
        if(temp == null){
            focusState.setFocusCount(1);
            focusState.setFansCount(0);
            focusStateMapper.insert(focusState);
        }else{
            focusState.setFansCount(temp.getFansCount());
            focusState.setFocusCount(temp.getFocusCount() + 1);
            focusStateMapper.updateById(focusState);
        }
    }

    @Override
    public void decrebyFan(String userIdTo) {
        FocusState focusState = new FocusState();
        focusState.setUserId(userIdTo);
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userIdTo);
        FocusState temp = focusStateMapper.selectOne(wrapper);
        if(temp == null){
            focusStateMapper.insert(focusState);
        }else{
            focusState.setFansCount(temp.getFansCount() - 1);
            focusState.setFocusCount(temp.getFocusCount());
            focusStateMapper.updateById(focusState);
        }
    }

    @Override
    public void decrebyFocus(String userIdFrom) {
        FocusState focusState = new FocusState();
        focusState.setUserId(userIdFrom);
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userIdFrom);
        FocusState temp = focusStateMapper.selectOne(wrapper);
        if(temp == null){
            focusStateMapper.insert(focusState);
        }else{
            focusState.setFansCount(temp.getFansCount());
            focusState.setFocusCount(temp.getFocusCount() - 1);
            focusStateMapper.updateById(focusState);
        }
    }

    @Override
    public Integer getFansCount(String id) {
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        FocusState focusState = focusStateMapper.selectOne(wrapper);
        if(focusState != null) {
            return focusState.getFansCount();
        } else {
            return 0;
        }
    }

    @Override
    public Integer getFocusCount(String id) {
        QueryWrapper<FocusState> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        FocusState focusState = focusStateMapper.selectOne(wrapper);
        if(focusState != null){
            return focusState.getFocusCount();
        }else{
            return 0;
        }
    }
}
