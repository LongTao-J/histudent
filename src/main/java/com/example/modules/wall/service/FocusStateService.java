package com.example.modules.wall.service;

import com.example.modules.wall.entity.po.FocusState;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 关注状态表 服务类
 * @author mushan
 * @since 2022-08-22
 */
public interface FocusStateService extends IService<FocusState> {

    void increbyFan(String userIdTo);

    void increbyFocus(String userIdFrom);

    void decrebyFan(String userIdTo);

    void decrebyFocus(String userIdFrom);

    Integer getFansCount(String id);

    Integer getFocusCount(String id);
}
