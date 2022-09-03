package com.example.modules.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoLt {
    private String headaddress;//头像 u
    private String nickname;//昵称 u
    private int sex;//性别 u
    private int age;//年龄 u
    private String introduction;//自我介绍 u
    private String schoolname;//学校名字
    private String professionname;//专业名字
    private String backimg;//主页背景 u
    private String classBackimg;//课表背景 u
}
