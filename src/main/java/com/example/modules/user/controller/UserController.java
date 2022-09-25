package com.example.modules.user.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Profession;
import com.example.modules.collegeInformation.pojo.School;
import com.example.modules.user.mapper.StuInfoMapper;
import com.example.modules.user.mapper.UserMapper;

import com.example.modules.user.pojo.dto.*;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;

import com.example.modules.user.utils.Anquan.ResponseResult;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private ProfessionMapper professionMapper;

    @Autowired
    private UserService userServiceImpl;


    //登录
    @PostMapping("/login")
    @CrossOrigin
    public ResponseResult login(@RequestBody User user){
        return userServiceImpl.login(user);
    }

    //退出
    @PostMapping("/logout")
    @CrossOrigin
    public R<String> logout(){
        userServiceImpl.logout();
        return R.success(null,"退出成功",200);
    }

    @PostMapping("/register")//注册
    @CrossOrigin
    public R<User> register(@RequestBody UserSms userSms){
        return userServiceImpl.RegisterSer(userSms);
    }

    //短信发送
    @GetMapping("/sendCode/{phone}")
    @CrossOrigin
    public R<Smss> duanxin(@PathVariable String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tSKh58f7gFHg2qpfw7k", "FgCcfuYtsoTAEgrzg2LfJhbP1ReDy0");
        IAcsClient client = new DefaultAcsClient(profile);
        Random random=new Random();
        Smss smss=new Smss();
        smss.setSms(String.valueOf(random.nextInt(9999-1000+1)+1000));//验证码

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("Hi同学科技");
        request.setTemplateCode("SMS_252635072");
        System.out.println("电话号码为："+phone);
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\""+smss.getSms()+"\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        ValueOperations<String,String> redis=redisTemplate.opsForValue();
        redis.set(phone,smss.getSms());
        return R.success(smss,"短信发送成功",200);
    }


    @GetMapping("/getUserInfo")//获取用户信息
    @CrossOrigin
    public R<UserInfoLt> UserInfo(){
        String userid=userServiceImpl.getTokenUser().getId();
        if(userid.equals("dont")){
            return R.error("请先登录",200);
        }
        UserInfoLt userInfoLt = userServiceImpl.getUserInfolt(userid);

        return R.success(userInfoLt,"获取成功",200);
    }

    //根据id查用户
    @GetMapping("/getUserById/{userId}")
    @CrossOrigin
    public R<User> getUserById(@PathVariable("userid") String userid){
        User user = userMapper.selectById(userid);
        return R.success(user,"获取信息成功",200);
    }


    //忘记密码，获取验证码后，然后修改密码
    @PostMapping("/setpas")
    @CrossOrigin
    public R<String> updateUserPassword(@RequestBody UserSms userSms){
        ValueOperations<String,String> redis=redisTemplate.opsForValue();
        String sqlsms=redis.get(userSms.getPhone());
        if (!sqlsms.equals(userSms.getSms())){
            return R.error("验证码错误",400);
        }

        LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper();
        wrapper.eq(User::getPhone,userSms.getPhone());
        User user = userMapper.selectOne(wrapper);
        user.setPassword(userSms.getPassword());
        userMapper.updateById(user);

        return R.success(null,"修改密码成功",200);
    }


    //修改性别
    @PutMapping("/upsex/{sex}")
    @CrossOrigin
    public R<String> upSex(@PathVariable("sex") int sex){
        String userid=userServiceImpl.getTokenUser().getId();
        User user=userMapper.selectById(userid);
        System.out.println(user);
        user.setSex(sex);
        userMapper.updateById(user);
        return R.success(null,"修改性别成功",200);
    }

    //修改年龄
    @PutMapping("/upage/{age}")
    @CrossOrigin
    public R<Object> upAge(@PathVariable("age") int age){
        return userServiceImpl.upUserAge(age);
    }

    //修改昵称
    @PutMapping("/upnickname/{name}")
    @CrossOrigin
    public R<String> upNickeName(@PathVariable("name") String name){
        String userid=userServiceImpl.getTokenUser().getId();
        User user=userMapper.selectById(userid);
        user.setNickname(name);
        userMapper.updateById(user);
        return R.success(null,"修改昵称成功",200);
    }

    //修改自我介绍
    @PutMapping("/upintroduction/{introduction}")
    @CrossOrigin
    public R<String> upintroduction(@PathVariable("introduction") String introduction){
        String userid=userServiceImpl.getTokenUser().getId();
        User user=userMapper.selectById(userid);
        System.out.println(user);
        user.setIntroduction(introduction);
        userMapper.updateById(user);
        return R.success(null,"修改自我介绍成功",200);
    }

    //修改我的学校
    @PutMapping("/upschool/{schoolname}")
    @CrossOrigin
    public R<String> upAge(@PathVariable("schoolname") String schoolname){
        String userid=userServiceImpl.getTokenUser().getId();

        User user = userMapper.selectById(userid);

        LambdaQueryWrapper<School> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(School::getName,schoolname);
        School school = schoolMapper.selectOne(wrapper);
        String scId=school.getId();

        LambdaQueryWrapper<StuInfo> swrapper=new LambdaQueryWrapper<>();
        swrapper.eq(StuInfo::getStuNum,user.getStuInfoId());
        StuInfo stuInfo=stuInfoMapper.selectOne(swrapper);
        stuInfo.setSchId(scId);
        stuInfoMapper.updateById(stuInfo);

        return R.success(null,"修改学校成功",200);
    }

    //修改专业
    @PutMapping("/upprofession/{professionname}")
    @CrossOrigin
    public R<String> upProfession(@PathVariable("professionname") String professionname){
        String userid=userServiceImpl.getTokenUser().getId();

        User user = userMapper.selectById(userid);

        LambdaQueryWrapper<Profession> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Profession::getName,professionname);
        Profession profession = professionMapper.selectOne(wrapper);
        String prId=profession.getId();

        LambdaQueryWrapper<StuInfo> swrapper=new LambdaQueryWrapper<>();
        swrapper.eq(StuInfo::getStuNum,user.getStuInfoId());
        StuInfo stuInfo=stuInfoMapper.selectOne(swrapper);
        stuInfo.setProfId(prId);
        stuInfoMapper.updateById(stuInfo);

        return R.success(null,"修改专业成功",200);
    }

    //修改入学日期
    @PutMapping("/upTime/{scTime}")
    @CrossOrigin
    public R<String> upTime(@PathVariable("scTime") String scTime){
        String userid=userServiceImpl.getTokenUser().getId();

        User user = userMapper.selectById(userid);
        user.setSchoolTime(scTime);
        userMapper.updateById(user);

        return R.success(null,"更新时间成功",200);
    }

    //删除学校，和入学时间
    @DeleteMapping("/deletSchoolTime")
    @CrossOrigin
    public R<String> deletSchoolTime(){

        String userid=userServiceImpl.getTokenUser().getId();

        User user = userMapper.selectById(userid);
        String stuId=user.getStuInfoId();

        user.setSchoolTime("");
        userMapper.updateById(user);

        LambdaQueryWrapper<StuInfo> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StuInfo::getStuNum,stuId);
        StuInfo stuInfo = stuInfoMapper.selectOne(wrapper);
        stuInfo.setSchId("100");
        stuInfoMapper.updateById(stuInfo);

        return R.success(null,"删除学校信息成功",200);
    }

    //上传头像
    @PostMapping("/upHeadAddress")
    @CrossOrigin
    public R<String> upHeadAddress(@RequestBody HeadImage headAddress){
        String userId=userServiceImpl.getTokenUser().getId();
        User user = userMapper.selectById(userId);
        user.setHeadaddress(headAddress.getImgurl());
        userMapper.updateById(user);
        return R.success(headAddress.getImgurl(),"头像上传成功",200);
    }

    //上传背景图片
    @PostMapping("/upBackImg")
    @CrossOrigin
    public R<String> upBackImg(@RequestBody BackImg backImg){
        String userId=userServiceImpl.getTokenUser().getId();
        User user = userMapper.selectById(userId);
        user.setBackimg(backImg.getBackimage());
        userMapper.updateById(user);
        return R.success(backImg.getBackimage(),"背景图片上传成功",200);
    }

    //上传课表背景图片
    @PostMapping("/upClassBackImg")
    @CrossOrigin
    public R<String> upClassBackImg(@RequestBody ClassBackImage classBackImage){
        try {
            String userId=userServiceImpl.getTokenUser().getId();
            User user = userMapper.selectById(userId);
            user.setClassBackimg(classBackImage.getClassImg());
            userMapper.updateById(user);
            return R.success(classBackImage.getClassImg(),"课表背景图片上传成功",200);
        }catch (Exception e){
            return R.error(classBackImage.getClassImg(),400);
        }
    }
}
