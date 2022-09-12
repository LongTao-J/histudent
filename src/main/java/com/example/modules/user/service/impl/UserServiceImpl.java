package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.dto.Longtt;
import com.example.modules.user.pojo.dto.UserInfoLt;
import com.example.modules.user.pojo.dto.UserSms;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Anquan.JwtUtil;
import com.example.modules.user.utils.Anquan.LoginUser;
import com.example.modules.user.utils.Anquan.RedisCache;
import com.example.modules.user.utils.Anquan.ResponseResult;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapperImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate redisTemplate;

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

    //登录
    @Override
    public ResponseResult login(User user) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getPhone(),user.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                return new ResponseResult(200,"用户名或密码错误",null);
            }
            //使用userid生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            //authenticate存入redis
            redisCache.setCacheObject("login:"+userId,loginUser);
            //把token响应给前端
            HashMap<String,String> map = new HashMap<>();
            map.put("token",jwt);
            return new ResponseResult(200,"登陆成功",map);
        }catch (Exception e){
            return new ResponseResult(200,"用户名或密码错误",null);
        }
    }

    //退出
    @Override
    public ResponseResult logout() {
        String userid = getTokenUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }

    //注册
    @Override
    public R<User> RegisterSer(UserSms userSms) {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String smslocal=redis.get(userSms.getPhone());
        if (smslocal==null || !smslocal.equals(userSms.getSms())){
            return R.error("验证码错误",400);
        }
        User user=new User();
        user.setPhone(userSms.getPhone());
        user.setPassword(userSms.getPassword());
        userMapperImpl.insert(user);
        return R.success(user);
    }

    //token查询用户
    @Override
    public User getTokenUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getUser();
    }

    @Override
    public R<Object> upUserAge(int age) {
        String userid=getTokenUser().getId();
        User user=userMapperImpl.selectById(userid);
        user.setAge(age);
        userMapperImpl.updateById(user);
        return R.success(null,"修改年龄成功",200);
    }
}
