package com.example.modules.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.notice.entity.po.Notice;

import java.util.List;

public interface NoticeService extends IService<Notice> {

    List<Notice> getAll();

    void insertNotice();
}
