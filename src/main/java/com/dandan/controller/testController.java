package com.dandan.controller;

import com.dandan.quartz.EmailJobDetail;
import com.dandan.service.QuartzServie;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author dandan
 * @Date 2024/7 /23 18:50
 * @Description
 */

@RestController
public class testController {

    @Autowired
    QuartzServie quartzServie;


    @GetMapping("/testshiro")
    @RequiresRoles("admin")
    public void test(){
        System.out.println("1111111");
    }


    /**
     * 测试定时任务得启动项目 不能用简单的测试
     */
    @GetMapping("testquartz")
    public void test1(){
        long time = System.currentTimeMillis();
        System.out.println(time);
        time+=60*1000*2;
        Map<String, Object> content = new HashMap<>();
        content.put("roomFloor","11");
        content.put("roomNo","302");
        content.put("startTime","2024-07-23 15:02:00");
        content.put("endTime","2024-07-23 15:02:00");
        content.put("result","申请通过");
        content.put("theme","asdasda");
        content.put("applyTime","2024-07-23 15:02:00");
        content.put("email","dandan@qq.com");
        quartzServie.startJob(time,"121",EmailJobDetail.class,content);
    }
}
