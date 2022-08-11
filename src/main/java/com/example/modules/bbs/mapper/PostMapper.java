package com.example.modules.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.bbs.entity.po.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
}
