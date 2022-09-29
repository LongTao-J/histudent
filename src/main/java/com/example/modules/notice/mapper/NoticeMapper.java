package com.example.modules.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.notice.entity.po.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
