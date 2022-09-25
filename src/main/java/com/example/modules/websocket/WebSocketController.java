package com.example.modules.websocket;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.modules.user.service.UserService;
import com.example.modules.websocket.entity.MegUser;
import com.example.modules.websocket.entity.MesssageWs;
import com.example.modules.websocket.entity.Msg;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userServiceImpl;

    //查询我的聊天列表
    @GetMapping("/getMyMessage")
    @CrossOrigin
    public List<MegUser> stringList(){
        String username=userServiceImpl.getTokenUser().getNickname();
        Set keys = redisTemplate.opsForHash().keys(username);
        List<MegUser> megUserList=new ArrayList<>();
        for (Object s:keys){
            String x= (String) s;
            MegUser megUser=new MegUser();
            megUser.setUsername(x);
            megUser.setHeadImg(userServiceImpl.getImgByUserName(x));

            List<String> list= (List<String>) redisTemplate.opsForHash().get(username,x);
            JSONObject jsonObject=JSONUtil.parseObj(list.get(0));
            megUser.setText(jsonObject.getStr("text"));

            megUserList.add(megUser);
        }
        return megUserList;
    }


    //查询某个的聊天室
    @GetMapping("/getMessage/{tousername}")
    @CrossOrigin
    public List<MesssageWs> messsageWs(@PathVariable("tousername") String tousername){
        String username=userServiceImpl.getTokenUser().getNickname();
        List<String> list= (List<String>) redisTemplate.opsForHash().get(username,tousername);
        List<MesssageWs> wsList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            MesssageWs messsageWs=new MesssageWs();
            JSONObject obj = JSONUtil.parseObj(list.get(i));
            messsageWs.setUsernameHeader(userServiceImpl.getImgByUserName(obj.getStr("from")));
            messsageWs.setTousernameHeader(userServiceImpl.getImgByUserName(obj.getStr("to")));
            messsageWs.setText(obj.getStr("text"));
            if (obj.getStr("from").equals(username)){
                messsageWs.setFlage(1);
            }else {
                messsageWs.setFlage(0);
            }
            wsList.add(messsageWs);
        }
        return wsList;
    }

    //保存聊天消息
    @PostMapping("/saveMessage")
    @CrossOrigin
    public R<Object> saveMessage(@RequestBody Msg msg){
        String toUsername=userServiceImpl.getTokenUser().getNickname();
        String username=msg.getFrom();
        String text=msg.getText();
        log.info("Controller收到用户username={}的消息:{}",username, text);
        JSONObject obj = JSONUtil.parseObj(msg);
            try {
                //聊天消息保存到redis
                obj.set("to", toUsername);
                List<String> listX= (List<String>) redisTemplate.opsForHash().get(username,toUsername);
                if (listX==null){
                    listX=new ArrayList<>();
                }
                listX.add(obj.toString());
                redisTemplate.opsForHash().put(username,toUsername,listX);

                List<String> listY= (List<String>) redisTemplate.opsForHash().get(toUsername,username);
                if (listY==null){
                    listY=new ArrayList<>();
                }
                listY.add(obj.toString());
                redisTemplate.opsForHash().put(toUsername,username,listY);
                return R.success("保存消息成功");
            }catch (Exception e){
                return R.error("保存消息失败",400);
            }
    }

    //删除与某人的聊天
    @DeleteMapping("/deleteMsg/{tousername}")
    @CrossOrigin
    public R<Object> deleteUsername(@PathVariable("tousername") String tousername){
        try {
            String username=userServiceImpl.getTokenUser().getNickname();
            redisTemplate.opsForHash().delete(username,tousername);
            return R.success("删除成功");
        }catch (Exception e){
            return R.error();
        }
    }

}
