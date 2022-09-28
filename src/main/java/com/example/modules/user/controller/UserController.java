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
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

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

    //短信发送,判断是注册(0)，还是修改密码(1)
    @GetMapping("/sendCode/{phone}/{flage}")
    @CrossOrigin
    public R<Object> duanxin(@PathVariable("phone") String phone,@PathVariable("flage") String flage){

        //查看手机号是否已经注册
        if (!userServiceImpl.PhoneIf(phone)&&flage.equals("0")){
            return R.success("手机号已被注册");
        }

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
        redis.set(phone,smss.getSms(),5, TimeUnit.MINUTES);
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
        User user = userServiceImpl.getUserById(userid);
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

        Boolean aBoolean = userServiceImpl.updateUserPasswordSer(userSms.getPassword());
        if (aBoolean){
            return R.success(null,"修改密码成功",200);
        }else {
            return R.error("修改密码失败",400);
        }
    }


    //修改性别
    @PutMapping("/upsex/{sex}")
    @CrossOrigin
    public R<String> upSex(@PathVariable("sex") int sex){
        Boolean aBoolean = userServiceImpl.upSexSer(sex);
        if (aBoolean){
            return R.success(null,"修改性别成功",200);
        }else {
            return R.error("修改性别失败",400);
        }
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
        Boolean aBoolean = userServiceImpl.upNickeNameSer(name);
        if (aBoolean){
            return R.success(null,"修改昵称成功",200);
        }else {
            return R.error("修改昵称失败",400);
        }
    }

    //修改自我介绍
    @PutMapping("/upintroduction/{introduction}")
    @CrossOrigin
    public R<String> upintroduction(@PathVariable("introduction") String introduction){
        Boolean aBoolean = userServiceImpl.upintroductionSer(introduction);
        if (aBoolean){
            return R.success(null,"修改自我介绍成功",200);
        }else {
            return R.error("修改自我介绍失败",400);
        }
    }

    //修改我的学校
    @PutMapping("/upschool/{schoolname}")
    @CrossOrigin
    public R<String> upSchool(@PathVariable("schoolname") String schoolname){
        Boolean aBoolean = userServiceImpl.upSchoolSer(schoolname);
        if (aBoolean){
            return R.success(null,"修改学校成功",200);
        }else {
            return R.error("修改学校失败",400);
        }

    }

    //修改专业
    @PutMapping("/upprofession/{professionname}")
    @CrossOrigin
    public R<String> upProfession(@PathVariable("professionname") String professionname){
        Boolean aBoolean = userServiceImpl.upProfessionSer(professionname);
        if (aBoolean){
            return R.success(null,"修改专业成功",200);
        }else {
            return R.error("修改专业失败",400);
        }
    }

    //修改入学日期
    @PutMapping("/upTime/{scTime}")
    @CrossOrigin
    public R<String> upTime(@PathVariable("scTime") String scTime){
        Boolean aBoolean = userServiceImpl.upTimeSer(scTime);
        if (aBoolean){
            return R.success(null,"更新时间成功",200);
        }else {
            return R.error("更新时间失败",400);
        }

    }

    //删除学校，和入学时间
    @DeleteMapping("/deletSchoolTime")
    @CrossOrigin
    public R<String> deletSchoolTime(){
        Boolean aBoolean = userServiceImpl.deletSchoolTimeSer();
        if (aBoolean){
            return R.success(null,"删除学校信息成功",200);
        }else {
            return R.error("删除学校信息失败",400);
        }

    }

    //上传头像
    @PostMapping("/upHeadAddress")
    @CrossOrigin
    public R<String> upHeadAddress(@RequestBody HeadImage headAddress){
        Boolean aBoolean = userServiceImpl.upHeadAddressSer(headAddress);
        if (aBoolean){
            return R.success(headAddress.getImgurl(),"头像上传成功",200);
        }else {
            return R.error("头像上传失败",400);
        }
    }

    //上传背景图片
    @PostMapping("/upBackImg")
    @CrossOrigin
    public R<String> upBackImg(@RequestBody BackImg backImg){
        Boolean aBoolean = userServiceImpl.upBackImgSer(backImg);
        if (aBoolean){
            return R.success(backImg.getBackimage(),"背景图片上传成功",200);
        }else {
            return R.error("背景图片上传失败",400);
        }
    }

    //上传课表背景图片
    @PostMapping("/upClassBackImg")
    @CrossOrigin
    public R<String> upClassBackImg(@RequestBody ClassBackImage classBackImage){
        Boolean aBoolean = userServiceImpl.upClassBackImgSer(classBackImage);
        if (aBoolean){
            return R.success(classBackImage.getClassImg(),"课表背景图片上传成功",200);
        }else {
            return R.error("背景图片上传失败",400);
        }
    }
}
