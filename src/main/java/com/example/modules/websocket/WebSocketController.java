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
        String userId=userServiceImpl.getTokenUser().getId();
        Set keys = redisTemplate.opsForHash().keys(userId);
        List<MegUser> megUserList=new ArrayList<>();
        for (Object s:keys){
            String x= (String) s;//id
            MegUser megUser=new MegUser();
            megUser.setUsername(userServiceImpl.selectNameById(x));
            megUser.setHeadImg(userServiceImpl.getImgByUserId(x));
            megUser.setUserId(x);
            List<String> list= (List<String>) redisTemplate.opsForHash().get(userId,x);
            int dext=list.size()-1;
            if (dext<0){
                dext=0;
            }
            JSONObject jsonObject=JSONUtil.parseObj(list.get(dext));
            megUser.setText(jsonObject.getStr("text"));

            megUserList.add(megUser);
        }
        return megUserList;
    }


    //查询某个的聊天室
    @GetMapping("/getMessage/{touserId}")
    @CrossOrigin
    public List<MesssageWs> messsageWs(@PathVariable("touserId") String touserId){
        String userid=userServiceImpl.getTokenUser().getId();//当前用户
        List<String> list= (List<String>) redisTemplate.opsForHash().get(userid,touserId);
        List<MesssageWs> wsList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            MesssageWs messsageWs=new MesssageWs();
            JSONObject obj = JSONUtil.parseObj(list.get(i));
            messsageWs.setHeader(userServiceImpl.getImgByUserId(obj.getStr("fromId")));
            messsageWs.setText(obj.getStr("text"));
            if (obj.getStr("fromId").equals(userid)){
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
        String toUsername= msg.getTo();
        String username=msg.getFrom();
        String toId=msg.getToId();
        String fromId=msg.getFromId();
        String text=msg.getText();
        log.info("Controller收到用户username={}的消息:{}",username, text);
        JSONObject obj = JSONUtil.parseObj(msg);
            try {
                //聊天消息保存到redis
                obj.set("to", toUsername);
                List<String> listX= (List<String>) redisTemplate.opsForHash().get(toId,fromId);
                if (listX==null){
                    listX=new ArrayList<>();
                }
                listX.add(obj.toString());
                redisTemplate.opsForHash().put(toId,fromId,listX);

                List<String> listY= (List<String>) redisTemplate.opsForHash().get(fromId,toId);
                if (listY==null){
                    listY=new ArrayList<>();
                }
                listY.add(obj.toString());
                redisTemplate.opsForHash().put(fromId,toId,listY);
                return R.success("保存消息成功");
            }catch (Exception e){
                return R.error("保存消息失败",400);
            }
    }

    //删除与某人的聊天
    @DeleteMapping("/deleteMsg/{touserid}")
    @CrossOrigin
    public R<Object> deleteUsername(@PathVariable("touserid") String touserid){
        try {
            String userId=userServiceImpl.getTokenUser().getId();
            redisTemplate.opsForHash().delete(userId,touserid);
            return R.success("删除成功");
        }catch (Exception e){
            return R.error();
        }
    }

}
