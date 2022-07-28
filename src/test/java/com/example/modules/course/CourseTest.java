package com.example.modules.course;

import com.example.modules.course.pojo.Course;
import com.example.modules.course.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseService courseService;

    @Test
    void serviceTest() {
        List<Course> courses = courseService.list();
        System.out.println(courses);
    }
}
