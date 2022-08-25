package com.example.modules.market.task;

import com.example.modules.market.service.CommodityWantService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

//@Slf4j
//public class WantTask extends QuartzJobBean {
//    @Autowired
//    CommodityWantService commodityWantServiceImpl;
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("---------------------------longt开始同步持久化数据库-------------------------");
//
//        //将 Redis 里的点赞信息同步到数据库里
//        commodityWantServiceImpl.transLikedFromRedis0DB();
//        commodityWantServiceImpl.transLikedCountFromRedis0DB();
//        log.info("---------------------------longt成功同步持久化数据库------------------------");
//    }
//}


public class WantTask  {

}