package com.example.modules.course.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.course.mapper.CourseMapper;
import com.example.modules.course.pojo.Course;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    @Autowired
    CourseMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public String toStringJson(List<Course> courses) {
        String json = "[";
        for (Course course : courses){
            json = json + "[" + JSON.toJSONString(course) + "],";
        }
        json = json.substring(0,json.length() -1);
        json = json + "]";
        return json;
    }

    // 查找课表
    @GetMapping("/get")
    @CrossOrigin
    public String autoAddCourse() {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        List<Course> courses = mapper.selectByUserIdList(userId);
        return toStringJson(courses);
    }

    // 静态直接获取
    @GetMapping("/staticGet")
    @CrossOrigin
    public String staticGetCourse() {
        List<Course> courses = mapper.selectByUserIdList("1552570983563436034");
        Map<String, List<Course>> tmp = new HashMap<>();
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
        return JSON.toJSONString(tmp);
    }


    // 自动导入课表
    @PostMapping("/autoadd/{year}/{cnt}/{username}/{password}")
    @CrossOrigin
    public String autoAddCourse(
                                   @PathVariable("year") String year,
                                   @PathVariable("cnt") String cnt,
                                   @PathVariable("username") String username,
                                   @PathVariable("password") String password) {

        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        QueryWrapper<Course> condition = new QueryWrapper<>();
        condition.eq("user_id", userId).last("limit 1");
        Integer integer = mapper.selectCount(condition);
        if(integer > 0) mapper.deleteByUserId(userId);

        if( cnt.equals("2") ) cnt = "4";
//        String pyTextPath = "/root/CourseAutoImport/sql_cource_avg.py";
        String pyTextPath = "C:\\python-project\\pyhont-hi\\CourseAutoImport\\sql_cource_avg.py";
        String evn = "C:\\envroment\\python";
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
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
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
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
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
