package com.example.modules.course;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.course.mapper.CourseMapper;
import com.example.modules.course.pojo.Course;
import org.junit.jupiter.api.Test;
import org.python.jline.internal.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void serviceTest() {
        List<Course> courses = courseMapper.selectList();
        System.out.println(courses);
    }

    @Test
    void empty() {
        QueryWrapper<Course> condition = new QueryWrapper<>();
        condition.eq("user_id", 2331).last("limit 1");
        Integer integer = courseMapper.selectCount(condition);
        System.out.println(integer + "------------------");
    }

    @Test
    void delete() {
        Integer integer =  courseMapper.deleteByUserId("2331");
        System.out.println(integer + "------------------");
    }


    void autoInsert(){
        String userId = "2332";
        String cnt = "1";
        String year = "2019";
        String username = "201902505223";
        String password = "MEIyoumima12";
        try {
            String[] args1=new String[]{"D:\\install\\conda_data\\envs\\py36\\python.exe","/CourseAutoImport/sql_cource_avg.py", userId, year, cnt, username, password};
//            String[] evn = new String[] {userId, year, cnt, username, password};
//            String cmd = "D:\\install\\conda_data\\envs\\py36\\python.exe D:\\bin\\sql_cource_avg.py";
            Process pr=Runtime.getRuntime().exec(args1);
//            Process pr=Runtime.getRuntime().exec(cmd, evn);
//            Process pr=Runtime.getRuntime().exec("D:\\install\\conda_data\\envs\\py36\\python.exe D:\\bin\\sql_cource_avg.py " + userId + " " + year + " " + cnt + " " + username + " " + password);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
