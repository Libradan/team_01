package com.dandan.quartz;

import com.dandan.service.EmailService;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author dandan
 * @Date 2024/7 /23 15:57
 * @Description
 */
@Component
public class EmailJobDetail implements Job {
    @Autowired
    EmailService emailService;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Map<String,Object> info = (Map<String, Object>) context.getJobDetail().getJobDataMap().get("info");


        //表示发的是通知开会的邮件
        if(info.get("result")== null){
           emailService.sendStartMail((String)info.get("depEmail"),"会议即将开始通知",info);
        }else{
           emailService.sendAuditMail((String)info.get("depEmail"),"会议室申请审核结果通知",info);
        }
    }
}
