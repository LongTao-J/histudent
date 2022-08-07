package com.example.modules.course.controller;

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
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    @Autowired
    CourseMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 查找课表
    @GetMapping("/get")
    public R<List<Course>> autoAddCourse() {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        List<Course> courses = mapper.selectByUserIdList(userId);
        if(courses != null)
            return R.success(courses);
        else
            return R.error();
    }


    // 自动导入课表
    @PostMapping("/autoadd/{year}/{cnt}/{username}/{password}")
    public R<List<Course>> autoAddCourse(
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
        if(courses != null)
            return R.success(courses);
        else
            return R.error();
    }

    // 手动导入课表
    @PostMapping("/add")
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
    public R<Course> updateCourse(@RequestBody Course course) {
        int code = mapper.updateById(course);
        if(code == 1)
            return R.success(course);
        else
            return R.error();
    }

    // 删除所有课程
    @DeleteMapping("/deleteList")
    public R<String> deleteListCourse() {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        mapper.deleteByUserId(userId);
        return R.success(userId);
    }

    // 删除课程
    @DeleteMapping("/delete/{id}")
    public R<String> deleteCourse(@PathVariable("id") String id) {
        int code = mapper.deleteById(id);
        if(code == 1)
            return R.success(id);
        else
            return R.error();
    }

}
