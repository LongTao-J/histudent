package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import com.example.modules.user.utils.Anquan.JwtUtil;
import com.example.modules.user.utils.Anquan.LoginUser;
import com.example.modules.user.utils.Anquan.RedisCache;
import com.example.modules.user.utils.Anquan.ResponseResult;
import com.example.modules.user.utils.Consts;
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
    @Autowired
    private StuInfoMapper stuInfoMapperImpl;
    @Autowired
    private SchoolMapper schoolMapperImpl;
    @Autowired
    private ProfessionMapper professionMapperImpl;

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
            String loginStatus= (String) redisTemplate.opsForHash().get(Consts.LOGIN_USERS,user.getPhone());
            if(loginStatus!=null && loginStatus.equals("yes")){
                return new ResponseResult(200,"用户已经在其他地方登录",null);
            }

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
            //登录的用户存到redis
            redisTemplate.opsForHash().put(Consts.LOGIN_USERS,user.getPhone(),"yes");
            return new ResponseResult(200,"登陆成功",map);
        }catch (Exception e){
            return new ResponseResult(200,"程序报错或用户名或密码错误",null);
        }
    }

    //退出
    @Override
    public ResponseResult logout() {
        User user= getTokenUser();
        redisCache.deleteObject("login:"+user.getId());
        redisTemplate.opsForHash().delete(Consts.LOGIN_USERS,user.getPhone());
        return new ResponseResult(200,"退出成功");
    }

    //注册
    @Override
    public R<User> RegisterSer(UserSms userSms) {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String smslocal=redis.get(userSms.getPhone());
        if (smslocal==null || !smslocal.equals(userSms.getSms())){
            return R.error("验证码错误或失效",400);
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

    @Override
    public String getImgByUserName(String username) {
        String imgByNickeName = userMapperImpl.getImgByNickeName(username);
        return imgByNickeName;
    }

    @Override
    public String getUSerNickName(String userId) {
        User user = userMapperImpl.selectById(userId);
        return user.getNickname();
    }

    @Override
    public Boolean PhoneIf(String phone) {
        try {
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userMapperImpl.selectOne(queryWrapper);
            if (user!=null){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User getUserById(String userId) {
        try {
            User user = userMapperImpl.selectById(userId);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean updateUserPasswordSer(String password) {
        try {
            User user=this.getTokenUser();
            user.setPassword(password);
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upSexSer(Integer sex) {
        try {
            User user=this.getTokenUser();
            user.setSex(sex);
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upNickeNameSer(String name) {
        try {
            User user=this.getTokenUser();
            user.setNickname(name);
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upintroductionSer(String introduction) {
        try {
            User user=this.getTokenUser();
            user.setIntroduction(introduction);
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upSchoolSer(String schoolname) {

        try {
            User user =this.getTokenUser();

            LambdaQueryWrapper<School> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(School::getName,schoolname);
            School school = schoolMapperImpl.selectOne(wrapper);
            String scId=school.getId();

            LambdaQueryWrapper<StuInfo> swrapper=new LambdaQueryWrapper<>();
            swrapper.eq(StuInfo::getStuNum,user.getStuInfoId());
            StuInfo stuInfo=stuInfoMapperImpl.selectOne(swrapper);
            stuInfo.setSchId(scId);
            stuInfoMapperImpl.updateById(stuInfo);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upProfessionSer(String professionname) {
        try {
            User user=this.getTokenUser();

            LambdaQueryWrapper<Profession> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Profession::getName,professionname);
            Profession profession = professionMapperImpl.selectOne(wrapper);
            String prId=profession.getId();

            LambdaQueryWrapper<StuInfo> swrapper=new LambdaQueryWrapper<>();
            swrapper.eq(StuInfo::getStuNum,user.getStuInfoId());
            StuInfo stuInfo=stuInfoMapperImpl.selectOne(swrapper);
            stuInfo.setProfId(prId);
            stuInfoMapperImpl.updateById(stuInfo);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upTimeSer(String scTime) {
        try {
            User user = this.getTokenUser();
            user.setSchoolTime(scTime);
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean deletSchoolTimeSer() {
        try {
            User user=this.getTokenUser();
            String stuId=user.getStuInfoId();

            user.setSchoolTime("");
            userMapperImpl.updateById(user);

            LambdaQueryWrapper<StuInfo> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(StuInfo::getStuNum,stuId);
            StuInfo stuInfo = stuInfoMapperImpl.selectOne(wrapper);
            stuInfo.setSchId("100");
            stuInfoMapperImpl.updateById(stuInfo);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upHeadAddressSer(HeadImage headAddress) {
        try {
            User user=this.getTokenUser();
            user.setHeadaddress(headAddress.getImgurl());
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upBackImgSer(BackImg backImg) {
        try {
            User user=this.getTokenUser();
            user.setBackimg(backImg.getBackimage());
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean upClassBackImgSer(ClassBackImage classBackImage) {
        try {
            User user=this.getTokenUser();
            user.setClassBackimg(classBackImage.getClassImg());
            userMapperImpl.updateById(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
