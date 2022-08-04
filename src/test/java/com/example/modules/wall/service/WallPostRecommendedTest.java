package com.example.modules.wall.service;

import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostRecommended;
import com.example.modules.walls.service.WallPostRecommendedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author mushan
 * @date 8/3/2022
 * @apiNote
 */
@SpringBootTest
public class WallPostRecommendedTest {
    @Autowired
    WallPostRecommendedService wallPostRecommendedServiceImpl;

    @Test
    void insertTest(){
//        WallPostRecommended wallPostRecommended = new WallPostRecommended();
//        WallPostRecommended wallPostRecommended1 = new WallPostRecommended();
//        WallPostRecommended wallPostRecommended2 = new WallPostRecommended();
//        WallPostRecommended wallPostRecommended3 = new WallPostRecommended();
//        wallPostRecommended.setWallPostId("05743b445e949915546b1a153ca4d065");
//        wallPostRecommended1.setWallPostId("1f6a9d4c4019863965dfbddfbc44ef1f");
//        wallPostRecommended2.setWallPostId("2623b432066a47edf04821a09f2fe525");
//        wallPostRecommended3.setWallPostId("61343b43c8175412bf8f0a87578e0dff");
//        wallPostRecommendedServiceImpl.insert(wallPostRecommended);
//        wallPostRecommendedServiceImpl.insert(wallPostRecommended1);
//        wallPostRecommendedServiceImpl.insert(wallPostRecommended2);
//        wallPostRecommendedServiceImpl.insert(wallPostRecommended3);
    }

    @Test
    void getTest(){
        List<WallPost> allRecommendedPost = wallPostRecommendedServiceImpl.getAllRecommendedPost();
        System.out.println(allRecommendedPost);
    }

    @Test
    void deleteTest(){
        wallPostRecommendedServiceImpl.deleteRecommendedById("084d9c4964320573062564d034784ad4");
    }

}
