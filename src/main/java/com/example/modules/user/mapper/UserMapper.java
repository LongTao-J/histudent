package com.example.modules.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.user.pojo.dto.Longtt;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.pojo.dto.UserInfoLt;
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

    @Select("SELECT * FROM stu_info LEFT JOIN `user` ON stu_info.stu_num=`user`.stu_info_id WHERE `user`.id=#{userid}")
    StuInfo getStuInFoMapper(String userid);

    //根据学号查学校和专业
    @Select("SELECT s.name AS schoolname,p.name AS professionname\n" +
            "FROM stu_info t LEFT JOIN profession p ON p.id=t.prof_id\n" +
            "left JOIN school s ON s.id=t.sch_id\n" +
            "WHERE t.stu_num= #{id}")
    Longtt getSchoolProfessiom(String id);

    @Select("SELECT headaddress FROM `user` WHERE nickname=#{username}")
    String getImgByNickeName(String username);
}
