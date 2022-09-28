package com.example.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.user.pojo.dto.*;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.utils.Anquan.ResponseResult;
import com.example.utils.R;

public interface UserService extends IService<User> {

    StuInfo getStuInfo(String userid);

    UserInfoLt getUserInfolt(String userid);

    ResponseResult login(User user);

    ResponseResult logout();

    R<String> RegisterSer(UserSms userSms);

    User getTokenUser();

    R<Object> upUserAge(int age);

    String getImgByUserName(String username);

    //查询用户的名字
    String getUSerNickName(String userId);

    //查看手机号是否注册
    Boolean PhoneIf(String phone);

    //根据id查用户
    User getUserById(String userId);

    //修改密码
    Boolean updateUserPasswordSer(String password);

    //修改性别
    Boolean upSexSer(Integer sex);

    //修改昵称
    Boolean upNickeNameSer(String name);

    //修改自我介绍
    Boolean upintroductionSer(String introduction);

    //修改我的学校
    Boolean upSchoolSer(String schoolname);

    //修改专业
    Boolean upProfessionSer(String professionname);

    //修改入学日期
    Boolean upTimeSer(String scTime);

    //删除学校，和入学时间
    Boolean deletSchoolTimeSer();

    //上传头像
    Boolean upHeadAddressSer(HeadImage headAddress);

    //上传背景图片
    Boolean upBackImgSer(BackImg backImg);

    //上传课表背景图片
    Boolean upClassBackImgSer(ClassBackImage classBackImage);
}
