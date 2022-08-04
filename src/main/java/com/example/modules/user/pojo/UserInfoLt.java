package com.example.modules.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoLt {
    private String id;
    private String headaddress;
    private String nickname;
    private int sex;
    private int age;
    private String introduction;
    private String schoolname;
    private String professionname;
}
