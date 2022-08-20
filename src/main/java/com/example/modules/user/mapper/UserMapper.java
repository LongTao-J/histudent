package com.example.modules.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.user.pojo.User;
import com.example.modules.user.pojo.UserInfoLt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT s.class_backimg AS classBackimg,s.backimg,s.headaddress,s.nickname,s.sex,s.age,s.introduction,x.name AS schoolname,p.name AS professionname\n" +
            "    from stu_info c LEFT JOIN `user` s ON s.stu_info_id = c.stu_num\n" +
            "    LEFT JOIN school x ON x.id = c.sch_id\n" +
            "\t\tLEFT JOIN profession p ON p.id = c.prof_id\n" +
            "    WHERE s.id= #{userid}")
    UserInfoLt userinfo(String userid);
}
