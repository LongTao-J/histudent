package com.example.modules.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.course.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> selectList();

    @Select("select * from user_course_scheduling where user_id = #{id}")
    List<Course> selectByUserIdList(@Param("id") String id);

    @Select("delete from user_course_scheduling where user_id = #{user_id}")
    Integer deleteByUserId(@Param("user_id") String userId);
}
