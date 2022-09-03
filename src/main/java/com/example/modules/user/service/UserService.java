package com.example.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.user.pojo.StuInfo;
import com.example.modules.user.pojo.User;
import com.example.modules.user.pojo.UserInfoLt;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {

    StuInfo getStuInfo(String userid);

    UserInfoLt getUserInfolt(String userid);

}
