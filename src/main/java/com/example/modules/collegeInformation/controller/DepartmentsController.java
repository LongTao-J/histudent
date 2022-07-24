package com.example.modules.collegeInformation.controller;

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
    public Integer updateDepartments(@PathVariable("id") String id, @PathVariable("name") String name) {
        Departments departments = new Departments(id, name);
        return departmentsMapper.updateById(departments);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Integer deleteDepartments(@PathVariable("id") String id) {
        QueryWrapper<Profession> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("dep_id", id);
        List<Profession> professions = professionMapper.selectList(queryWrapper2);
        for (Profession profession1 : professions) {
            professionMapper.deleteById(profession1.getId());
        }
        return departmentsMapper.deleteById(id);
    }

    @GetMapping(value = "/get/{id}")
    public Departments queryDepartmentsById(@PathVariable("id") String id){
        return departmentsMapper.selectById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Departments> queryDepartmentsAll(){
        return departmentsMapper.selectList();
    }
}
