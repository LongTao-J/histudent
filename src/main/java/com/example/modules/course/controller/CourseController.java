package com.example.modules.course.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.course.mapper.CourseMapper;
import com.example.modules.course.pojo.Course;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.python.jline.internal.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    @Autowired
    CourseMapper mapper;

    @Autowired
    UserService userServiceImpl;

    public String toStringJson(List<Course> courses) {
        String json = "[";
        for (Course course : courses){
            json = json + "[" + JSON.toJSONString(course) + "],";
        }
        json = json.substring(0,json.length() -1);
        json = json + "]";
        return json;
    }

    // 判断本周是否有此课程
    public boolean judgeInString(String str, int num) {
//        String str = "1-10周(双),12-13周";
//        String num = "8";
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
        for (int j: list){
            if (num == j){
                return true;
            }
        }
        return false;
    }

    // 过滤课程
    public List<Course> filterCourse(List<Course> list, int num){
        List<Course> tmpList = new ArrayList<>();
        for(Course course: list){
            if(judgeInString(course.getWeekly(), num)){
                tmpList.add(course);
            }
        }
        return tmpList;
    }

    // 排版
    public String arrangement(List<Course> courses){
        Map<String, List<Course>> tmp = new HashMap<>();
        // 数据排版
        for (Course course : courses){
            List<Course> courseList;
            if(!tmp.containsKey(course.getPeriod())) {
                courseList = new ArrayList<>();
            }else {
                courseList = tmp.get(course.getPeriod());
            }
            courseList.add(course);
            tmp.put(course.getPeriod(),courseList);
        }
        // 对值排序
        for(Map.Entry<String, List<Course>> map: tmp.entrySet()){
            List<Course> courseList = map.getValue();
            courseList.sort(Comparator.comparingInt((Course s) -> Integer.parseInt(s.getWeekNum())).thenComparing(Course::getName));
        }
        // 对键排序
        Collection<String> keys = tmp.keySet();
        List<String> list = new ArrayList<>(keys);
        Collections.sort(list);
        StringBuilder json = new StringBuilder("{");
        for(String s:  list){
            json.append("\"").append(s).append("\":").append(JSON.toJSONString(tmp.get(s))).append(",");
        }
        json = new StringBuilder(json.substring(0, json.length() - 1) + "}");
        if(json.length() == 1)return "{}";
        return json.toString();
    }

    // 查找课表
    @GetMapping("/get/{num}")
    @CrossOrigin
    public String getCourse(@PathVariable("num") Integer num) {
        String userId = userServiceImpl.getTokenUser().getId();
//         String userId = "1552570983563436034";
        List<Course> courses = mapper.selectByUserIdList(userId);
        return arrangement(filterCourse(courses, num));
    }

    // 静态直接获取
    @GetMapping("/staticGet")
    @CrossOrigin
    public String staticGetCourse() {
        List<Course> courses = mapper.selectByUserIdList("1552570983563436034");
        Map<String, List<Course>> tmp = new HashMap<>();
        // 数据排版
        for (Course course : courses){
            List<Course> courseList;
            if(!tmp.containsKey(course.getPeriod())) {
                courseList = new ArrayList<>();
            }else {
                courseList = tmp.get(course.getPeriod());
            }
            courseList.add(course);
            tmp.put(course.getPeriod(),courseList);
        }
        // 对值排序
        for(Map.Entry<String, List<Course>> map: tmp.entrySet()){
            List<Course> courseList = map.getValue();
            courseList.sort(Comparator.comparingInt((Course s) -> Integer.parseInt(s.getWeekNum())).thenComparing(Course::getName));
        }
        // 对键排序
        Collection<String> keys = tmp.keySet();
        List<String> list = new ArrayList<>(keys);
        Collections.sort(list);
        StringBuilder json = new StringBuilder("{");
        for(String s:  list){
            json.append("\"").append(s).append("\":").append(JSON.toJSONString(tmp.get(s))).append(",");
        }
        json = new StringBuilder(json.substring(0, json.length() - 1) + "}");

        return json.toString();
    }


    // 自动导入课表
    @PostMapping("/autoadd/{year}/{cnt}/{username}/{password}")
    @CrossOrigin
    public String autoAddCourse(
            @PathVariable("year") String year,
            @PathVariable("cnt") String cnt,
            @PathVariable("username") String username,
            @PathVariable("password") String password) {

        String userId = userServiceImpl.getTokenUser().getId();
        QueryWrapper<Course> condition = new QueryWrapper<>();
        condition.eq("user_id", userId).last("limit 1");
        Integer integer = mapper.selectCount(condition);
        if(integer > 0) mapper.deleteByUserId(userId);

        if( cnt.equals("2") ) cnt = "4";
        String pyTextPath = "D:\\bin\\CourseAutoImport\\sql_cource_avg.py";
        String evn = "D:\\install\\conda_data\\envs\\py36\\python";
//        String pyTextPath = "C:\\python-project\\pyhont-hi\\CourseAutoImport\\sql_cource_avg.py";
//        String evn = "C:\\envroment\\python";
        try {
            String[] args1=new String[]{evn, pyTextPath, userId, year, cnt, username, password};
            Process pr=Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            in.close();
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        List<Course> courses = mapper.selectByUserIdList(userId);
//        if(courses != null)
//            return R.success(courses);
//        else
//            return R.error();
        return toStringJson(courses);
    }

    // 手动导入课表
    @PostMapping("/add")
    @CrossOrigin
    public R<Course> addCourse(@RequestBody Course course) {
        String userId = userServiceImpl.getTokenUser().getId();
        course.setUserId(userId);
        int code = mapper.insert(course);
        if(code == 1)
            return R.success(course);
        else
            return R.error();
    }

    // 更新课表
    @PutMapping("/update")
    @CrossOrigin
    public R<Course> updateCourse(@RequestBody Course course) {
        int code = mapper.updateById(course);
        if(code == 1)
            return R.success(course);
        else
            return R.error();
    }

    // 删除所有课程
    @DeleteMapping("/deleteList")
    @CrossOrigin
    public R<String> deleteListCourse() {
        String userId = userServiceImpl.getTokenUser().getId();
        mapper.deleteByUserId(userId);
        return R.success(userId);
    }

    // 删除课程
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public R<String> deleteCourse(@PathVariable("id") String id) {
        int code = mapper.deleteById(id);
        if(code == 1)
            return R.success(id);
        else
            return R.error();
    }

}