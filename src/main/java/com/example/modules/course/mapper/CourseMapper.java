package com.example.modules.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.course.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
