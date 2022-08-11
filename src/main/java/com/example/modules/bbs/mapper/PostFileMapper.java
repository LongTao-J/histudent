package com.example.modules.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.bbs.entity.po.PostFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostFileMapper extends BaseMapper<PostFile> {
    @Select("select url from `post_file` where post_id = #{postId}")
    List<String> selectFileAllByPostId(String postId);
}
