package com.example.modules.todolist.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.todolist.mapper.ToDoListMapper;
import com.example.modules.todolist.entity.Todolist;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todo")
public class ToDoListController {

    @Autowired
    private ToDoListMapper toDoListMapper;



    @Autowired
    UserService userServiceImpl;

    //获取所有todo
    @GetMapping("/getall")
    @CrossOrigin
    public R<List<Todolist>> getAll(){
        String userId = userServiceImpl.getTokenUser().getId();
        List<Todolist> toDo = toDoListMapper.getToDo(userId);

        return R.success(toDo,"查询所有成功",200);
    }

    //新增todo
    @PostMapping("/add/{title}")
    @CrossOrigin
    public R<Todolist> addTodO(@PathVariable("title") String title){
        String userId = userServiceImpl.getTokenUser().getId();
        Todolist toDoList=new Todolist();
        toDoList.setUserId(userId);
        toDoList.setTitle(title);
        toDoListMapper.insert(toDoList);
        return R.success(toDoList,"创建成功",200);
    }

    //修改todo
    @PutMapping("/update/{index}/{title}")
    @CrossOrigin
    public R<Todolist> upToDa(@PathVariable("index") int index, @PathVariable("title") String title){

        String userid = userServiceImpl.getTokenUser().getId();

        List<Todolist> toDo = toDoListMapper.getToDo(userid);

        String todoID=toDo.get(index).getId();

        Todolist toDoList = toDoListMapper.selectById(todoID);
        toDoList.setTitle(title);
        toDoListMapper.updateById(toDoList);

        return R.success(toDoList,"修改内容成功",200);
    }

    //删除todo
    @DeleteMapping("/delete/{index}")
    @CrossOrigin
    public R<Todolist> deleteTodo(@PathVariable("index") int index){
        String userid = userServiceImpl.getTokenUser().getId();

        List<Todolist> toDo = toDoListMapper.getToDo(userid);
        String todoID=toDo.get(index).getId();
        //
        Todolist todolist = toDoListMapper.selectById(todoID);
        toDoListMapper.deleteById(todoID);

        //
        String ts= String.valueOf(index);

        return R.success(todolist,ts,200);
    }

    //大卡todo
    @PutMapping("/daka/{index}")
    @CrossOrigin
    public R<Todolist> Daka(@PathVariable("index") int index){

        String userid = userServiceImpl.getTokenUser().getId();

        List<Todolist> toDo = toDoListMapper.getToDo(userid);
        String todoID=toDo.get(index).getId();

        Todolist toDoList = toDoListMapper.selectById(todoID);
        toDoList.setCompleted(true);
        toDoListMapper.updateById(toDoList);

        return R.success(toDoList,"打卡成功",200);
    }
    //删除所有todo
    @DeleteMapping("/deleteAll")
    @CrossOrigin
    public R<String> DeleteAll(){
        String userid = userServiceImpl.getTokenUser().getId();
        QueryWrapper<Todolist> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        toDoListMapper.delete(queryWrapper);
        return R.success(null,"清空全部完成",200);
    }

    //删除已完成
    @DeleteMapping("/deleteTrue")
    @CrossOrigin
    public R<String> DeleteTrue(){
        String userid = userServiceImpl.getTokenUser().getId();
        QueryWrapper<Todolist> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("completed","1").eq("user_id",userid);
        toDoListMapper.delete(queryWrapper);

        return R.success(null,"清空已完成完成",200);
    }

    //取消已完成的
    @PutMapping("/cancelTd/{index}")
    @CrossOrigin
    public R<String> CancelTode(@PathVariable("index") int index){
        String userid = userServiceImpl.getTokenUser().getId();

        List<Todolist> toDo = toDoListMapper.getToDo(userid);
        String todoID=toDo.get(index).getId();

        Todolist todolist = toDoListMapper.selectById(todoID);
        todolist.setCompleted(false);
        toDoListMapper.updateById(todolist);

        return R.success(null,"取消成功",200);
    }

    //删除已完成
    @DeleteMapping("/deleteTrue2")
    @CrossOrigin
    public R<String> DeleteTrue2(@RequestBody List<Integer> integers){
        String userid = userServiceImpl.getTokenUser().getId();
        QueryWrapper<Todolist> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("completed","1").eq("user_id",userid);
        toDoListMapper.delete(queryWrapper);

        return R.success(null,"清空已完成完成",200);
    }
}
