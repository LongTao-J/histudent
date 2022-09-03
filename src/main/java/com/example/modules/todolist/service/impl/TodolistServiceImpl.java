package com.example.modules.todolist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.todolist.mapper.ToDoListMapper;
import com.example.modules.todolist.entity.Todolist;
import com.example.modules.todolist.service.TodolistService;
import org.springframework.stereotype.Service;

@Service
public class TodolistServiceImpl extends ServiceImpl<ToDoListMapper, Todolist> implements TodolistService {
}
