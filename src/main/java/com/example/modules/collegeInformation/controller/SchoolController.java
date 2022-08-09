package com.example.modules.collegeInformation.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.mapper.DepartmentsMapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import com.example.modules.collegeInformation.pojo.School;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/school")
public class SchoolController {

    @Autowired
    public ProfessionMapper professionMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;

    // 添加学校信息
    @PostMapping(value = "/add/{name}")
    @CrossOrigin
    public R<String> addSchool(@PathVariable("name") String name) {
        School school = new School(name);
        int code = schoolMapper.insert(school);
        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }
    // 修改
    @PutMapping(value = "/update/{id}/{name}")
    @CrossOrigin
    public R<String> updateSchool(@PathVariable("id") String id, @PathVariable("name") String name) {
        School school = new School(id, name);
        int code = schoolMapper.updateById(school);
        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }

    // 删除
    @DeleteMapping(value = "/delete/{id}")
    @CrossOrigin
    public R<String> deleteSchool(@PathVariable("id") String id) {
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sch_id", id);
        List<Departments> departments = departmentsMapper.selectList(queryWrapper);
        for (Departments departments1 : departments) {
            QueryWrapper<Profession> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("dep_id", departments1.getId());
            List<Profession> professions = professionMapper.selectList(queryWrapper2);
            for (Profession profession1 : professions) {
                professionMapper.deleteById(profession1.getId());
            }
            departmentsMapper.deleteById(departments1.getId());
        }
        int code = schoolMapper.deleteById(id);
        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }

    // 根据id获取信息
    @GetMapping(value = "/get/{id}")
    @CrossOrigin
    public R<School> querySchoolById(@PathVariable("id") String id){
        return R.success(schoolMapper.selectById(id));
    }

    // 获取所有学校信息
    @GetMapping(value = "/getAll")
    @CrossOrigin
    public R<List<School>> querySchoolAll(){
        return R.success(schoolMapper.selectList());
    }


    ////龙涛临时加的
    //获取学校名字
    @GetMapping("/getschoolname")
    @CrossOrigin
    public R<List<String>> getAllSchooll(){
        List<String> schools = schoolMapper.getSchoolname();
        return R.success(schools,"查询学校信息成功",200);
    }
    ////龙涛临时加的
    //搜素学校名字
    @GetMapping("/selectSchool/{shuzhi}")
    @CrossOrigin
    public R<List<String>> selectSchool(@PathVariable("shuzhi") String shuzhi){
        List<String> schools=schoolMapper.selectSchoolname("%"+shuzhi+"%");
        return R.success(schools,"查询成功",200);
    }


}
