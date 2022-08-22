package com.example.modules.wall.repository.impl;

import com.example.modules.wall.repository.FocusRepository;
import com.example.modules.wall.service.FocusService;
import com.example.modules.wall.service.FocusStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mushan
 * @date 22/8/2022
 * @apiNote
 */
@Repository
public class FocusRepositoryImpl implements FocusRepository {
    @Autowired
    FocusService focusServiceImpl;
    @Autowired
    FocusStateService focusStateServiceImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void focus(String userIdFrom, String userIdTo) {
        try {
            focusServiceImpl.focus(userIdFrom, userIdTo);
            focusStateServiceImpl.increbyFan(userIdTo);
            focusStateServiceImpl.increbyFocus(userIdFrom);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unfocus(String userIdFrom, String userIdTo) {
        try {
            focusServiceImpl.unfocus(userIdFrom, userIdTo);
            focusStateServiceImpl.decrebyFan(userIdTo);
            focusStateServiceImpl.decrebyFocus(userIdFrom);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
