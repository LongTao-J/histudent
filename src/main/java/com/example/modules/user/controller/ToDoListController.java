package com.example.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.user.mapper.ToDoListMapper;
import com.example.modules.user.pojo.Todolist;
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
    private RedisTemplate redisTemplate;

    //获取所有todo
    @GetMapping("/getall")
    @CrossOrigin
    public R<List<Todolist>> getAll(){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userid = redis.get(Consts.REDIS_USER);
        QueryWrapper<Todolist> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userid);
        List<Todolist> toDoListList = toDoListMapper.selectList(wrapper);

        return R.success(toDoListList,"查询所有成功",200);
    }

    //新增todo
    @PostMapping("/add/{title}")
    @CrossOrigin
    public R<Todolist> addTodO(@PathVariable("title") String title){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userid = redis.get(Consts.REDIS_USER);
        Todolist toDoList=new Todolist();
        toDoList.setUserId(userid);
        toDoList.setTitle(title);
        toDoListMapper.insert(toDoList);
        return R.success(toDoList,"创建成功",200);
    }

    //修改todo
    @PutMapping("/update/{id}/{title}")
    @CrossOrigin
    public R<Todolist> upToDa(@PathVariable("id") String id, @PathVariable("title") String title){
        Todolist toDoList = toDoListMapper.selectById(id);
        toDoList.setTitle(title);
        toDoListMapper.updateById(toDoList);

        return R.success(toDoList,"修改内容成功",200);
    }

    //删除todo
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public R<Todolist> deleteTodo(@PathVariable("id") String id){
        toDoListMapper.deleteById(id);

        return R.success(null,"删除成功",200);
    }

    //大卡todo
    @PutMapping("/daka/{id}")
    @CrossOrigin
    public R<Todolist> Daka(@PathVariable("id") String id){
        Todolist toDoList = toDoListMapper.selectById(id);
        toDoList.setCompleted(true);
        toDoListMapper.updateById(toDoList);

        return R.success(toDoList,"打卡成功",200);
    }
}
