package com.example.modules.collegeInformation.controller;

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

        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", sch);
        School school = schoolMapper.selectOne(queryWrapper);
        QueryWrapper<Departments> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", dep);
        Departments departments = departmentsMapper.selectOne(queryWrapper2);
        QueryWrapper<Profession> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("name", pro);
        Profession profession = professionMapper.selectOne(queryWrapper3);

        if(school == null){
            school = new School(sch);
            schoolMapper.insert(school);
        }

        if(departments == null) {
            departments = new Departments(dep);
            departments.setSch_id(school.getId());
            departmentsMapper.insert(departments);
        }

        if(profession == null) {
            profession = new Profession(pro);
            profession.setDep_id(departments.getId());
            professionMapper.insert(profession);
        }else {
            return "已存在";
        }

        return "ok";
    }

    @PutMapping(value = "/update/{id}/{name}")
    public Integer updateProfession(@PathVariable("id") String id, @PathVariable("name") String name) {
        Profession profession = new Profession(id, name);
        return professionMapper.updateById(profession);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Integer deleteProfession(@PathVariable("id") String id) {
        return professionMapper.deleteById(id);
    }

    @GetMapping(value = "/get/{id}")
    public Profession queryProfessionById(@PathVariable("id") String id){
        return professionMapper.selectById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Profession> queryProfessionAll(){
        return professionMapper.selectList();
    }
}
