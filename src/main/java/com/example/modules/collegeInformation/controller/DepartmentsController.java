package com.example.modules.collegeInformation.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.mapper.DepartmentsMapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentsController {

    @Autowired
    public ProfessionMapper professionMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;


    @PutMapping(value = "/update/{id}/{name}")
    public String updateDepartments(@PathVariable("id") String id, @PathVariable("name") String name) {
        Departments departments = new Departments(id, name);
        int code = departmentsMapper.updateById(departments);
        return "{code:" + code + "}";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteDepartments(@PathVariable("id") String id) {
        QueryWrapper<Profession> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("dep_id", id);
        List<Profession> professions = professionMapper.selectList(queryWrapper2);
        for (Profession profession1 : professions) {
            professionMapper.deleteById(profession1.getId());
        }
        int code = departmentsMapper.deleteById(id);
        return "{code:" + code + "}";
    }

    @GetMapping(value = "/get/{id}")
    public String queryDepartmentsById(@PathVariable("id") String id){
        return JSON.toJSONString(departmentsMapper.selectById(id));
    }

    @GetMapping(value = "/getAll/{sch_id}")
    public String queryDepartmentsAll(@PathVariable("sch_id") String id){
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sch_id", id);
        List<Departments> departmentsList = departmentsMapper.selectList(queryWrapper);
        return JSON.toJSONString(departmentsList);
    }
}
