package com.example.modules.walls.mapper;

import com.example.modules.walls.model.WallPostWithUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
@Mapper
public interface WallPostWithUserMapper {
    @Select("select wall_post.id,user.id AS userid,user.headaddress,user.nickname,wall_post.title,wall_post.content,\n" +
            "wall_post.like_count,wall_post.comments_count,wall_post.version,\n" +
            "wall_post.gmt_create,wall_post.gmt_modified from wall_post LEFT JOIN wall_post_recommended ON\n" +
            "wall_post.id=wall_post_recommended.wall_post_id LEFT JOIN `user` ON user.id =wall_post.\n" +
            "user_id;\n")
    List<WallPostWithUser> selectWallPostWithUsersByRecommended();
}
