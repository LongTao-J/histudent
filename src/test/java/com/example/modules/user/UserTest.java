package com.example.modules.user;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.example.modules.walls.mapper.WallPostFileMapper;
import com.example.modules.walls.model.WallPostFile;
import com.google.gson.Gson;
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
}
