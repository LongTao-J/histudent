package com.example.modules.wall.service;

import com.example.modules.user.pojo.User;
import com.example.modules.wall.entity.po.Focus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 关注表 服务类
 * @author mushan
 * @since 2022-08-22
 */
public interface FocusService extends IService<Focus> {

    void focus(String userIdFrom, String userIdTo);

    void unfocus(String userIdFrom, String userIdTo);

    List<User> getFocusList(String userId);

    List<User> getFansList(String userId);
}
