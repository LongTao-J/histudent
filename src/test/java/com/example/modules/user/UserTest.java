package com.example.modules.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.user.mapper.ToDoListMapper;
import com.example.modules.user.pojo.Todolist;
import com.example.modules.user.pojo.User;
import com.example.modules.user.service.UserService;
import com.example.modules.walls.mapper.WallPostFileMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    WallPostFileMapper wallPostFileMapper;

    @Test
    void test01(){
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tSKh58f7gFHg2qpfw7k", "FgCcfuYtsoTAEgrzg2LfJhbP1ReDy0");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        SendSmsRequest request = new SendSmsRequest();
//        request.setSignName("Hi龙涛");
//        request.setTemplateCode("SMS_244670258");
//        request.setPhoneNumbers("18110359126");
//        request.setTemplateParam("{\"code\":\"1234\"}");
//
//        try {
//            SendSmsResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
    }

    @Test
    public void test02(){
//        String wallPostId="77433534535";
//        List<String> images=new ArrayList<>();
//        images.add("111111111");
//        images.add("22222222222");
//        images.add("33333333333");
//        WallPostFile wallPostFile = new WallPostFile();
//        wallPostFile.setWallPostId(wallPostId);
//        wallPostFile.setUrl("12312xexx");
//        wallPostFileMapper.insert(wallPostFile);

    }

    @Autowired
    ToDoListMapper toDoListMapper;

    @Test
    void test03(){
//        Todolist toDoList = new Todolist();
//        toDoList.setUserId("12");
//        toDoList.setTitle("qwert");
//        toDoListMapper.insert(toDoList);
    }

    @Autowired
    UserService userServiceImpl;

    @Test
    void test04(){
//        User byId = userServiceImpl.getById("1");
//        System.out.println(byId);
    }


    @Test
    void test06(){
//        toDoListMapper.delete(null);
    }

    @Test
    void test05(){
        for (int i=0;i<10;i++){
            String x= String.valueOf(i*99);
            Todolist todolist=new Todolist();
            todolist.setUserId("1552570983563436034");
            todolist.setTitle(x);
            toDoListMapper.insert(todolist);
        }

    }

    @Test
    void Test06(){
//        QueryWrapper<Todolist> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("completed",true).eq("user_id","123");
//        toDoListMapper.delete(queryWrapper);
    }

    @Test
    void Test07(){
//        QueryWrapper<Todolist> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("user_id","1552570983563436034");
//        List<Todolist> todolists = toDoListMapper.selectList(queryWrapper);
//        int len=todolists.size()-1;
//        System.out.println(todolists.get(len-0));
    }
}
