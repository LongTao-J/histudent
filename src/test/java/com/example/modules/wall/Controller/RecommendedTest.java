package com.example.modules.wall.Controller;

import com.example.modules.walls.controller.WallPostCommentsController;
import com.example.modules.walls.controller.WallPostRecommendedController;
import com.example.modules.walls.mapper.WallPostCommentsMapper;
import com.example.modules.walls.model.WallPostRecommended;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author mushan
 * @date 8/3/2022
 * @apiNote
 */
@SpringBootTest
public class RecommendedTest {
    @Autowired
    WallPostRecommendedController wallPostRecommendedController;

    @Test
    void insert(){
        wallPostRecommendedController.addRecommended("e4559b0ed9d34c87a20b558bce304f9b");
    }

    @Test
    void query(){
        System.out.println(wallPostRecommendedController.queryAllRecommended());
    }

    @Test
    void delete(){
        wallPostRecommendedController.deleteRecommended("890702654905434bc8dad81c76354bd7");
    }
}
