package com.example.modules.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.epidemic.entity.po.Nucleic;
import com.example.modules.epidemic.mapper.NucleicMapper;
import com.example.modules.epidemic.service.NucleicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生核酸表 服务实现类
 * @author mushan
 * @since 2022-08-20
 */
@Service
public class NucleicServiceImpl extends ServiceImpl<NucleicMapper, Nucleic> implements NucleicService {
    @Autowired
    NucleicMapper nucleicMapper;

    @Override
    public void addNucleic(String schoolId, String stuNum, Boolean state) {
        Nucleic nucleic = new Nucleic();
        nucleic.setSchId(schoolId);
        nucleic.setStuNum(stuNum);
        nucleic.setState(state);
        nucleicMapper.insert(nucleic);
    }

    @Override
    public List<Nucleic> getNucleicList(String schId, String stuNum) {
        QueryWrapper<Nucleic> wrapper = new QueryWrapper<>();
        wrapper.eq("sch_id", schId);
        wrapper.eq("stu_num", stuNum);
        return nucleicMapper.selectList(wrapper);
    }
}
