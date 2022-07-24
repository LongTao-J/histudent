package com.example.modules.collegeInformation.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.mapper.DepartmentsMapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import com.example.modules.collegeInformation.pojo.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/profession")
public class ProfessionController {

    @Autowired
    public ProfessionMapper professionMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;

    // 整体加入
    @PostMapping(value = "/add/{school}/{departments}/{profession}")
    public String addProfession(
            @PathVariable("school") String sch,
            @PathVariable("departments") String dep,
            @PathVariable("profession") String pro) {
        int code = 0;
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", sch);
        School school = schoolMapper.selectOne(queryWrapper);

        if(school == null){
            school = new School(sch);
            schoolMapper.insert(school);
        }

        Departments departments = departmentsMapper.queryByIdAndName(school.getId(), dep);
        if(departments == null) {
            departments = new Departments(dep);
            departments.setSch_id(school.getId());
            departmentsMapper.insert(departments);
        }

        Profession profession = professionMapper.queryByIdAndName(departments.getId(), pro);
        if(profession == null) {
            profession = new Profession(pro);
            profession.setDep_id(departments.getId());
            code = professionMapper.insert(profession);
        }else {
            code = 404;
        }

        return "{code:" + code + "}";
    }

    @PutMapping(value = "/update/{id}/{name}")
    public String updateProfession(@PathVariable("id") String id, @PathVariable("name") String name) {
        Profession profession = new Profession(id, name);
        int code = professionMapper.updateById(profession);
        return "{code:" + code + "}";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteProfession(@PathVariable("id") String id) {
        int code = professionMapper.deleteById(id);
        return "{code:" + code + "}";
    }

    @GetMapping(value = "/get/{id}")
    public String queryProfessionById(@PathVariable("id") String id){
        return JSON.toJSONString(professionMapper.selectById(id));
    }

    @GetMapping(value = "/getAll/{dep_id}")
    public String queryProfessionAll(@PathVariable("dep_id") String id){
        QueryWrapper<Profession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dep_id", id);
        List<Profession> professions = professionMapper.selectList(queryWrapper);
        return JSON.toJSONString(professions);
    }
}
