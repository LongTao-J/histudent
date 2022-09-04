package com.example.modules.countDown.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.countDown.entity.dto.CountDownDTO;
import com.example.modules.countDown.entity.po.CountDown;
import com.example.modules.countDown.entity.vo.CountDownIdVO;
import com.example.modules.countDown.mapper.CountDownMapper;
import com.example.modules.countDown.service.CountDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CountDownServiceImpl extends ServiceImpl<CountDownMapper, CountDown> implements CountDownService {

    @Autowired
    CountDownMapper countDownMapper;

    @Override
    public boolean addCdSer(CountDownDTO countDownDTO,String userid) {
        try {
            CountDown countDown=new CountDown();
            countDown.setAddress(countDownDTO.getAddress());
            countDown.setContent(countDownDTO.getContent());
            countDown.setUserId(userid);
            countDown.setRemindtime(countDownDTO.getRemindtime());
            countDown.setSubjectTime(countDownDTO.getSubjectTime());
            countDownMapper.insert(countDown);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean upCdSer(CountDown countDownIdVO) {
        try {
            CountDown countDown = countDownMapper.selectById(countDownIdVO.getId());
            countDown.setRemindtime(countDownIdVO.getRemindtime());
            countDown.setAddress(countDownIdVO.getAddress());
            countDown.setRemindtime(countDownIdVO.getRemindtime());
            countDown.setSubjectTime(countDownIdVO.getSubjectTime());
            countDownMapper.updateById(countDown);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<CountDownIdVO> getmyCd(String userid) {
        try {
            QueryWrapper<CountDown> wrapper=new QueryWrapper<>();
            wrapper.eq("user_id",userid);
            List<CountDown> countDowns = countDownMapper.selectList(wrapper);
            List<CountDownIdVO> countDownIdVOS=new ArrayList<>();
            //处理
            for (int i=0;i<countDowns.size();i++){
                String Ctime=countDowns.get(i).getRemindtime();
                CountDownIdVO countDownIdVO=new CountDownIdVO();

                //remindtime1;//某一天
                String st1=Ctime.substring(5,7);
                st1=st1+"月";
                String st2=Ctime.substring(8,10);
                st2+="日";
                String sers=st1+st2;
                //
                countDownIdVO.setRemindtime1(sers);

                //remindtime2;//周几 + 几点
                //周几
                String nian=Ctime.substring(0,4);
                String yue=Ctime.substring(5,7);
                String ri=Ctime.substring(8,10);
                String zj=testt(nian,yue,ri);
                //几点
                String st3=Ctime.substring(11,13);
                String st4=Ctime.substring(11,17);
                String sx="";
                int x= Integer.parseInt(st3);
                if(x>=0&&x<=12){
                    sx="上午 "+st4;
                }else {
                    sx="下午 "+st4;
                }
                zj=zj+"  "+sx;
                //
                countDownIdVO.setRemindtime2(zj);

                //remindtime3;//剩几天
                countDownIdVO.setRemindtime3("剩"+tys9(nian+"-"+yue+"-"+ri)+"天");

                countDownIdVO.setContent(countDowns.get(i).getContent());
                countDownIdVO.setAddress(countDowns.get(i).getAddress());
                countDownIdVO.setId(countDowns.get(i).getId());
                countDownIdVO.setSubjectTime(countDowns.get(i).getSubjectTime());
                countDownIdVOS.add(countDownIdVO);
            }

            return countDownIdVOS;
        }catch (Exception e){
            return null;
        }
    }

    //求周几
    public String testt(String ni,String yu,String ri){
        int year= Integer.parseInt(ni),month= Integer.parseInt(yu),day= Integer.parseInt(ri);
        boolean leap=(year%400==0||(year%4==0&&year%100!=0));
        int total=(year-1980)+(year-1980+3)/4;
        for(int i=month-1;i>0;i--){
            switch (i){
                case 1: case 3: case 5: case 7: case 8: case 10: case 12: total+=31;break;
                case 4: case 6: case 9: case 11: total+=30;break;
                case 2:total+=leap?29:28;break;
            }
        }
        total+=day;
        int week=1;
        week=(week+total)%7;
        String sp="";
        switch (week){
            case 1:sp="周一";break;
            case 2:sp="周二";break;
            case 3:sp="周三";break;
            case 4:sp="周四";break;
            case 5:sp="周五";break;
            case 6:sp="周六";break;
            case 0:sp="周日";break;
        }
        return sp;
    }

    //相差天数
    String tys9(String xsy){
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String ris=today.toString();//获取当天时间
        try {
            Date star = dft.parse(ris);//开始时间
            Date endDay=dft.parse(xsy);//结束时间
            Long starTime=star.getTime();
            Long endTime=endDay.getTime();
            Long num=endTime-starTime;//时间戳相差的毫秒数
            num=num/24/60/60/1000;
            return num.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
