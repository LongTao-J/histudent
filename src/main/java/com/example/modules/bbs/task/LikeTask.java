package com.example.modules.bbs.task;

import com.example.modules.bbs.service.PostLikeService;
import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


@Slf4j
public class LikeTask extends QuartzJobBean {
    @Autowired
    PostLikeService postLikeServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("-----------------------------执行同步---------------------");

        //将 Redis 里的点赞信息同步到数据库里
        postLikeServiceImpl.transLikedFromRedis0DB();
        postLikeServiceImpl.transLikedCountFromRedis0DB();
        System.out.println("-----------------------------成功同步---------------------");
    }
}
