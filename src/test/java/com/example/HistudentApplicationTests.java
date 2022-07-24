package com.example;

import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.School;
import com.example.modules.collegeInformation.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HistudentApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	SchoolMapper schoolMapper;

	@Autowired
	SchoolService schoolService;

	@Test
	void addSchool(){
		School school = new School("name");
		int insert = schoolMapper.insert(school);
		schoolMapper.insert(school);
	}

}
