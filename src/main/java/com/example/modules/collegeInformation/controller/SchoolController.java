package com.example.modules.collegeInformation.controller;

import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.School;
import com.example.modules.collegeInformation.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private SchoolMapper schoolMapper;

    @PostMapping(value = "/add/{name}")
    public School addSchool(@PathVariable("name") String name) {
        School school = new School(name);
        schoolMapper.insert(school);
        return school;
    }

    @PutMapping(value = "/update/{id}/{name}")
    public School update(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        School school = new School(name);
//        schoolMapper.update();
        return school;
    }

}
