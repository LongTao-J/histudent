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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseMapper courseMapper;

//    @Test
    void serviceTest() {
        List<Course> courses = courseMapper.selectList();
        System.out.println(courses);
    }

//    @Test
    void empty() {
        QueryWrapper<Course> condition = new QueryWrapper<>();
        condition.eq("user_id", 2331).last("limit 1");
        Integer integer = courseMapper.selectCount(condition);
        System.out.println(integer + "------------------");
    }

//    @Test
    void delete() {
        Integer integer =  courseMapper.deleteByUserId("2331");
        System.out.println(integer + "------------------");
    }

    //    @Test
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

//    @Test
    void filterString() {
        String str = "1-6周,7-10周(双),12-13周";
        Integer num = 8;

        int i = 0,tag = 0;
        List<Integer> list = new ArrayList<>();
        while(i < str.length()) {
            char t = str.charAt(i);
            int tmp_num = 0;
            if (Character.isDigit(t)) {
                if (Character.isDigit(str.charAt(i+1))) {
                    tmp_num = (t - '0')*10 + (str.charAt(++i) - '0');
                }else {
                    tmp_num = t - '0';
                }
                if (tag == 1) {
                    int index = list.get(list.size() - 1);
                    boolean one = false, two = false;
                    if(i+3 < str.length()){
                        one = str.charAt(i+3) == '单';
                        two = str.charAt(i+3) == '双';
                    }
                    if(one && index%2 == 0)list.remove(list.size() - 1);
                    else if(two && index%2 != 0)list.remove(list.size() - 1);
                    for (int j = index+1; j <= tmp_num; j++) {
                        if(one && j%2 == 0) continue;
                        else if(two && j%2 != 0) continue;
                        list.add(j);
                    }
                    tag = 0;
                }else {
                    list.add(tmp_num);
                }
            } else if (t == '-') {
                tag = 1;
            }
            i++;
        }
//        if(one || two) {
//            Iterator<Integer> iterator = list.iterator();
//            while (iterator.hasNext()){
//                int tmp = iterator.next();
//                if(one && tmp%2 == 0){
//                    iterator.remove();
//                }else if(two && tmp%2 != 0){
//                    iterator.remove();
//                }
//            }
//        }

        for (int j: list){
//            if (num == j){
//                return;
//            }
            System.out.println(j + "----");
        }
    }
}
