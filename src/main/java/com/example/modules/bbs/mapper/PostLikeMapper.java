package com.example.modules.bbs.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.modules.bbs.entity.po.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostLikeMapper extends BaseMapper<PostLike> {

    @Select("SELECT * FROM post_like ${ew.customSqlSegment}")
    List<PostLike> selectPageVo(IPage<PostLike> page, @Param("ew") Wrapper<PostLike> queryWrapper);
}
