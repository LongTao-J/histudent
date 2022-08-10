package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.user.mapper.ToDoListMapper;
import com.example.modules.user.pojo.Todolist;
import com.example.modules.user.service.TodolistService;
import org.springframework.stereotype.Service;

@Service
public class TodolistServiceImpl extends ServiceImpl<ToDoListMapper, Todolist> implements TodolistService {
}
