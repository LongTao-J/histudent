package com.example.modules.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.wall.entity.po.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
}
