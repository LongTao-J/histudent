package com.example.modules.epidemic.controller;


import com.example.modules.epidemic.entity.dto.AddNucleicFromViewDTO;
import com.example.modules.epidemic.entity.po.Nucleic;
import com.example.modules.epidemic.service.NucleicService;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.service.UserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 学生核酸表 前端控制器
 * @author mushan
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/api/epidemic/nucleic")
public class NucleicController {

    @Autowired
    UserService userServiceImpl;
    @Autowired
    NucleicService nucleicServiceImpl;

    @PutMapping("/put/do-nucleic")
    @CrossOrigin
    public R<Object> addNucleic(@RequestBody AddNucleicFromViewDTO dto){
        try{
            nucleicServiceImpl.addNucleic(dto.getSchId(), dto.getStuNum(), dto.getState(), dto.getUnit());
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/nucleic-7day")
    @CrossOrigin
    public R<Object> getSevenDayNucleicList(){
        // redis获取当前用户id
        String userId = userServiceImpl.getTokenUser().getId();
        StuInfo stuInfo = userServiceImpl.getStuInfo(userId);
        String schId = stuInfo.getSchId();
        String stuNum = stuInfo.getStuNum();
        List<Nucleic> list = nucleicServiceImpl.getNucleicList(schId, stuNum);
        return R.success(list);
    }

    @GetMapping("/get/nucleic-last-date")
    @CrossOrigin
    public R<Object> getNucleicLastTime(){
        String userId = userServiceImpl.getTokenUser().getId();
        StuInfo stuInfo = userServiceImpl.getStuInfo(userId);
        String schId = stuInfo.getSchId();
        String stuNum = stuInfo.getStuNum();
        List<Nucleic> list = nucleicServiceImpl.getNucleicList(schId, stuNum);
        list.sort((t1, t2) -> t2.getGmtCreate().compareTo(t1.getGmtCreate()));
        Nucleic nucleic = list.get(0);
        if(nucleic == null){
            return R.success(-1);
        }
        else{
            Date last = nucleic.getGmtCreate();
            Date now = new Date();
            int days = (int) ((now.getTime() - last.getTime()) / (1000 * 60 * 60 * 24));
            return R.success(days);
        }
    }
}

