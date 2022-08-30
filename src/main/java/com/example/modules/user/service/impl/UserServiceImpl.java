package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.Longtt;
import com.example.modules.user.pojo.StuInfo;
import com.example.modules.user.pojo.User;
import com.example.modules.user.pojo.UserInfoLt;
import com.example.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapperImpl;

    @Override
    public StuInfo getStuInfo(String userid) {
        StuInfo stuInfo = userMapperImpl.getStuInFoMapper(userid);
        return stuInfo;
    }

    //查询用户信息
    @Override
    public UserInfoLt getUserInfolt(String userid) {

            UserInfoLt userInfoLt=new UserInfoLt();
            User user=userMapperImpl.selectById(userid);

            userInfoLt.setAge(user.getAge());
            userInfoLt.setBackimg(user.getBackimg());
            userInfoLt.setHeadaddress(user.getHeadaddress());
            userInfoLt.setClassBackimg(user.getClassBackimg());
            userInfoLt.setNickname(user.getNickname());
            userInfoLt.setIntroduction(user.getIntroduction());
            userInfoLt.setSex(user.getSex());

            try {
                //学校和专业
                Longtt longtt=new Longtt();
                longtt=userMapperImpl.getSchoolProfessiom(user.getStuInfoId());
                userInfoLt.setProfessionname(longtt.getProfessionname());
                userInfoLt.setSchoolname(longtt.getSchoolname());
            }catch (Exception e){
                return userInfoLt;
            }

            return userInfoLt;

    }
}
