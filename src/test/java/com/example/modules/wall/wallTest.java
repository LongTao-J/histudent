package com.example.modules.wall;

import com.example.modules.walls.mapper.WallPostCommentsMapper;
import com.example.modules.walls.mapper.WallPostLikeMapper;
import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostComments;
import com.example.modules.walls.model.WallPostLike;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author mushan
 * @date 7/23/2022
 * @apiNote
 */
@SpringBootTest
public class wallTest {
//    @Autowired
//    private WallPostMapper wallPostMapper;
//    @Autowired
//    private WallPostCommentsMapper wallPostCommentsMapper;
//    @Autowired
//    private WallPostLikeMapper wallPostLikeMapper;
//
//    @Test
//    void selectWallPost(){
//        List<WallPost> wallPosts = wallPostMapper.selectList(null);
//        wallPosts.forEach(System.out::println);
//    }
//
//    @Test
//    void insertWallPost(){
//        WallPost wallPost = new WallPost();
//        wallPost.setUserId("1");
//        wallPost.setTitle("关于木杉");
//        wallPost.setContent("一个菜鸡");
//        wallPostMapper.insert(wallPost);
//    }
//
//    @Test
//    void insertWallPostComments(){
//        WallPostComments wallPostComments = new WallPostComments();
//        wallPostComments.setUserId("1");
//        wallPostComments.setWallPostId("f48a322793518bb9b5e8b96b8a3afbb6");
//        wallPostComments.setContent("抢到沙发了!!!");
//        wallPostCommentsMapper.insert(wallPostComments);
//    }
//
//    @Test
//    void insetWallPostLike(){
//        WallPostLike wallPostLike = new WallPostLike();
//        wallPostLike.setUserId("1");
//        wallPostLike.setWallPostId("f48a322793518bb9b5e8b96b8a3afbb6");
//        wallPostLikeMapper.insert(wallPostLike);
//        WallPost wallPost = wallPostMapper.selectById("f48a322793518bb9b5e8b96b8a3afbb6");
//        wallPost.setLikeCount(wallPost.getLikeCount() + 1);
//        wallPostMapper.updateById(wallPost);
//    }
}
