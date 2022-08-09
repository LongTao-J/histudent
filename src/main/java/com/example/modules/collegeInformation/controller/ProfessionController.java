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
    @CrossOrigin
    public R<String> addProfession(
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

        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }

    @PutMapping(value = "/update/{id}/{name}")
    @CrossOrigin
    public R<String> updateProfession(@PathVariable("id") String id, @PathVariable("name") String name) {
        Profession profession = new Profession(id, name);
        int code = professionMapper.updateById(profession);
        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }

    @DeleteMapping(value = "/delete/{id}")
    @CrossOrigin
    public R<String> deleteProfession(@PathVariable("id") String id) {
        int code = professionMapper.deleteById(id);
        if(code == 1)
            return R.success(null);
        else
            return R.error();
    }

    @GetMapping(value = "/get/{id}")
    @CrossOrigin
    public R<Profession> queryProfessionById(@PathVariable("id") String id){
        return R.success(professionMapper.selectById(id));
    }

    @GetMapping(value = "/getAll/{dep_id}")
    @CrossOrigin
    public R<List<Profession>> queryProfessionAll(@PathVariable("dep_id") String id){
        QueryWrapper<Profession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dep_id", id);
        List<Profession> professions = professionMapper.selectList(queryWrapper);
        return R.success(professions);
    }
}
