package com.example.modules.wall.mapper;

import com.example.modules.user.pojo.po.User;
import com.example.modules.wall.entity.po.Focus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 关注表 Mapper 接口
 * @author mushan
 * @since 2022-08-22
 */
public interface FocusMapper extends BaseMapper<Focus> {

    @Select("select `user`.* from `user`, `focus` where `focus`.user_id_from = #{userId} and `focus`.user_id_to = `user`.id")
    List<User> selectAllFocus(String userId);

    @Select("select `user`.* from `user`, `focus` where `focus`.user_id_to = #{userId} and `focus`.user_id_from = `user`.id")
    List<User> selectAllFans(String userId);
}
