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
import com.example.modules.user.pojo.Smss;
import com.example.modules.user.pojo.TokenPj;
import com.example.modules.user.pojo.User;
import com.example.modules.user.pojo.UserSms;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.modules.user.utils.TokenUtil;
import com.example.utils.R;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    @CrossOrigin
    public R<TokenPj> login(HttpServletRequest request, @RequestBody User user){

        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,user.getPhone());
        User user1=userService.getOne(queryWrapper);//数据库user
        if (user1==null){
            return R.error("用户不存在",400);
        }

        if (!user1.getPassword().equals(user.getPassword())){
            return R.error("密码错误",400);
        }

        request.getSession().setAttribute(Consts.SESSION_USER,user1.getId());
        System.out.println("登陆成功");

        //token
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("userid",user1.getId());
        String token= TokenUtil.createJavaWebToken(m);
        TokenPj tokenPj=new TokenPj();
        tokenPj.setToken(token);
        return R.success(tokenPj,"登陆成功",200);
    }

    @PostMapping("/register")//注册
    @CrossOrigin
    public R<String> register(HttpServletRequest requests, @RequestBody UserSms userSms){
//        String sms1= (String) requests.getSession().getAttribute(Consts.SESSION_SMS);
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String sms1=redis.get(userSms.getPhone());
        System.out.println("========session："+sms1+"  my"+userSms.getSms());
        if (!userSms.getSms().equals(sms1)){
            return R.error("验证码错误",400);
        }
        User user=new User();
        user.setPhone(userSms.getPhone());
        user.setPassword(userSms.getPassword());
        userService.save(user);
        return R.success(null,"注册成功",400);
    }

    //短信发送
    @GetMapping("/sendCode/{phone}")
    @CrossOrigin
    public R<Smss> duanxin(HttpServletRequest requests, @PathVariable String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tSKh58f7gFHg2qpfw7k", "FgCcfuYtsoTAEgrzg2LfJhbP1ReDy0");
        IAcsClient client = new DefaultAcsClient(profile);
        Random random=new Random();
        Smss smss=new Smss();
        smss.setSms(String.valueOf(random.nextInt(9999-1000+1)+1000));//验证码

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
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
//        requests.getSession().setAttribute(Consts.SESSION_SMS,smss.getSms());
        ValueOperations<String,String> redis=redisTemplate.opsForValue();
        redis.set(phone,smss.getSms());
        return R.success(smss,"短信发送成功",200);
    }

    @PostMapping("/logout")
    @CrossOrigin
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute(Consts.SESSION_USER);
        return R.success(null,"退出成功",200);
    }

    @GetMapping("/getUserInfo")//获取用户信息
    @CrossOrigin
    public R<User> UserInfo(HttpServletRequest request){
        Long userid= (Long) request.getSession().getAttribute(Consts.SESSION_USER);
        if(userid==null){
            return R.error("请先登录",200);
        }
        User user = userService.getById(userid);
        return R.success(user,"获取成功",200);
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
        wrapper.eq(User::getPhone,userSms.getPhone()).set(User::getPassword,userSms.getPassword());
        userService.update(wrapper);

        return R.success(null,"修改密码成功",200);
    }
}
