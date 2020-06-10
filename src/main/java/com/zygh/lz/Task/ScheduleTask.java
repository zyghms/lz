package com.zygh.lz.Task;

import com.zygh.lz.controller.DepartmentOrPersonController;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Description:定时任务
 * User:luanhuajuan
 * Date:2020-06-09 23:00
 */
//@EnableAsync        // 2.开启多线程 由于业务量不大暂时不需要开启多线程
@Configuration
@EnableScheduling
public class ScheduleTask {



     // @Scheduled(cron = "*/5 * * * * ?") //每隔5秒中执行一次 用于测试
    @Scheduled(cron = "0 0 5 * * ?")  //3.每天早上5点更新一次 正式
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void configureDepartmentTasks() {
    String  params="{\n" +
            "    \"thirdId\": \"xindajiean\",\n" +
            "    \"lastTime\": \"0\",\n" +
            "    \"pageNo\": 1,\n" +
            "    \"pageSize\": 10\n" +
            "    \n" +
            "}";
     DepartmentOrPersonController.getDepartmentinfosyn(params);
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());

    }

     //@Scheduled(cron = "*/5 * * * * ?") //每隔5秒中执行一次 用于测试
     @Scheduled(cron = "0 0 6 * * ?")  //3.每天早上6点更新一次
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void configurePersonTasks() {
     String params ="{\n" +
             "    \"thirdId\": \"xindajiean\",\n" +
             "    \"lastTime\": \"0\",\n" +
             "    \"pageNo\": 1,\n" +
             "    \"pageSize\": 10,\n" +
             "    \"code\": \"\",\n" +
             "    \"cardNo\": \"\"\n" +
             "}";
     DepartmentOrPersonController.getPersoninfosyn(params);
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());

    }
}
