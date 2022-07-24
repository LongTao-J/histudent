package com.example.modules.collegeInformation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.mapper.DepartmentsMapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import com.example.modules.collegeInformation.pojo.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CollTest {
    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    DepartmentsMapper departmentsMapper;

    @Autowired
    ProfessionMapper professionMapper;

    public String sch = "name";
    public String dep = "dep2";
    public String pro = "pro3";

    @Test
    void add(){
        School school = new School("name3");
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "name5");
        School school3 = schoolMapper.selectOne(queryWrapper);
        int cnt = Math.toIntExact(schoolMapper.selectCount(queryWrapper));
        System.out.println("--------------");
        System.out.println(school3);
        System.out.println("--------------");
//        schoolMapper.insert(school);
        System.out.println("-------------------"+school.getId()+"--------------");
//        schoolService.save(school);
        School school2 = new School("37d0fdd1f46e249cf2cd1ca426e4ec3f", "name2");
//        schoolMapper.updateById(school2);
//        schoolMapper.selectList();
    }

    @Test
    void addAll() {
        Profession profession = new Profession(pro);

        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", sch);
        School school = schoolMapper.selectOne(queryWrapper);
        QueryWrapper<Departments> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", dep);
        Departments departments = departmentsMapper.selectOne(queryWrapper2);

        if(school == null){
            school = new School(sch);
            schoolMapper.insert(school);
        }

        if(departments == null) {
            departments = new Departments(dep);
            departments.setSch_id(school.getId());
            departmentsMapper.insert(departments);
        }

        profession.setDep_id(departments.getId());
        professionMapper.insert(profession);
    }

    @Test
    void delete(){
        String id = "834edabcaad229cea96a58e79c5b53ab";
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
        schoolMapper.deleteById(id);
    }

    @Test
    void idAndName(){
        Profession profession =  professionMapper.queryByIdAndName("f6375062255ca63b47e0cefb8c4913ef", "pro1");
        System.out.println("==================");
        System.out.println(profession);
        System.out.println("==================");
    }
}
