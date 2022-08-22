package com.example.modules.epidemic.mapper;

import com.example.modules.epidemic.entity.po.Nucleic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生核酸表 Mapper 接口
 * @author mushan
 * @since 2022-08-20
 */
@Mapper
public interface NucleicMapper extends BaseMapper<Nucleic> {

    @Delete("delete from nucleic where DATE(gmt_create) <= DATE(DATE_SUB(NOW(), INTERVAL 7 DAY ));")
    public void clearDataFromSevenDaysAgo();
}
