package com.example.modules.wall;

import com.example.modules.wall.mapper.WallPostMapper;
import com.example.modules.wall.model.WallPost;
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
    @Autowired
    private WallPostMapper wallPostMapper;

    @Test
    void selectWallPost(){
        List<WallPost> wallPosts = wallPostMapper.selectList(null);
        wallPosts.forEach(System.out::println);
    }
}
