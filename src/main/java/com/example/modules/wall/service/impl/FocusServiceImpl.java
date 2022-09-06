package com.example.modules.wall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.user.pojo.po.User;
import com.example.modules.wall.entity.po.Focus;
import com.example.modules.wall.mapper.FocusMapper;
import com.example.modules.wall.service.FocusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关注表 服务实现类
 * @author mushan
 * @since 2022-08-22
 */
@Service
public class FocusServiceImpl extends ServiceImpl<FocusMapper, Focus> implements FocusService {
    @Autowired
    FocusMapper focusMapper;

    @Override
    public void focus(String userIdFrom, String userIdTo) {
        Focus focus = new Focus();
        focus.setUserIdFrom(userIdFrom);
        focus.setUserIdTo(userIdTo);
        focusMapper.insert(focus);
    }

    @Override
    public void unfocus(String userIdFrom, String userIdTo) {
        QueryWrapper<Focus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id_from", userIdFrom);
        wrapper.eq("user_id_to", userIdTo);
        focusMapper.delete(wrapper);
    }

    @Override
    public List<User> getFocusList(String userId) {
        return focusMapper.selectAllFocus(userId);
    }

    @Override
    public List<User> getFansList(String userId) {
        return focusMapper.selectAllFans(userId);
    }
}
