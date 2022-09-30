package com.example.modules.notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.modules.notice.entity.po.Notice;
import com.example.modules.notice.service.NoticeService;
import com.example.modules.user.service.UserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeServiceImpl;

    @GetMapping("/getAllNotice")
    @CrossOrigin
    public R<List<Notice>> getAllNotice(){
        List<Notice> all = noticeServiceImpl.getAll();
        if (all==null){
            return R.error();
        }
        return R.success(all);
    }
}
