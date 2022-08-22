package com.example.modules.epidemic.service;

import com.example.modules.epidemic.entity.po.Nucleic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 学生核酸表 服务类
 * @author mushan
 * @since 2022-08-20
 */
public interface NucleicService extends IService<Nucleic> {

    void addNucleic(String schId, String stuNum, Boolean state);

    List<Nucleic> getNucleicList(String schId, String stuNum);
}
