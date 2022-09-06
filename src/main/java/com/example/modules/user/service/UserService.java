package com.example.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.pojo.dto.UserInfoLt;
import com.example.modules.user.pojo.dto.UserSms;
import com.example.modules.user.utils.Anquan.ResponseResult;


public interface UserService extends IService<User> {

    StuInfo getStuInfo(String userid);

    UserInfoLt getUserInfolt(String userid);

    ResponseResult login(User user);

    ResponseResult logout();

    boolean RegisterSer(UserSms userSms);
}
