package com.example.modules.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;//用户编号
    private String stuInfoId;//学生信息编号
    private int level;//级别
    private String phone;//手机号
    private String password;//密码
    private String nickname;//昵称
    private int age;//年龄
    private int sex;//性别(0:secret 1:man 2:woman)
    private String headaddress;//头像
    private String introduction;
    private String schoolTime;
    private String backimg;
    @Version
    private Integer version;//乐观锁

//    private String sms;

//    private int uid;
//    private String username;
//    private String password;
//    private String phone;
//    private String sms;//验证码
//    private int status;
}
