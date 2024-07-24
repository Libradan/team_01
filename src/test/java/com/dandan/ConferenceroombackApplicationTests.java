package com.dandan;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandan.common.exception.SystemException;
import com.dandan.entity.Admin;
import com.dandan.entity.ConferenceRoom;
import com.dandan.entity.Employee;
import com.dandan.entity.group.ConRApplyRecord;
import com.dandan.service.*;
import com.dandan.util.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ConferenceroombackApplicationTests {

    @Autowired
    ConferenceRoomService conferenceRoomService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ApplyService applyService;

    @Autowired
    ConferenceRecordService conferenceRecordService;

    @Autowired
    EmailService emailService;

    @Autowired
    QuartzServie quartzServie;

    @Test
    void contextLoads() {

        List<ConferenceRoom> list = conferenceRoomService.list(new QueryWrapper<ConferenceRoom>()

                //第一个参数为true则拼接  这里为null或者""则返回true
                .eq(false,"room_type", "大多媒体教室")
                .eq(false,"room_floor", "11")
                .eq(false,"room_size",200)
        );

        System.out.println(list);
    }

    @Test
    void testDistinct(){
        List<ConferenceRoom> distinct_room_floor = conferenceRoomService.list(
                new QueryWrapper<ConferenceRoom>()
                        .select("distinct room_floor,room_type,room_size"));

        System.out.println(distinct_room_floor);
    }


    @Test
    void testTime(){
        Integer roomId = 3;
         DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         LocalDateTime startTime = LocalDateTime.parse("2024-07-20 10:00:00", DATETIME_FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse("2024-07-23 09:00:00", DATETIME_FORMATTER);

        applyService.searchTimeConflict(roomId,startTime,endTime);
        /*select a.apply_id
        from apply a ,conference_record cre ,conference_room cro
        where a.apply_id = cre.apply_id
        and cro.room_id=?
        and (cre.start_time between DATE_SUB(?, INTERVAL 30 MINUTE)and DATE_ADD(?, INTERVAL 30 MINUTE)
        or cre.end_time between DATE_SUB(?, INTERVAL 30 MINUTE)and DATE_ADD(?, INTERVAL 30 MINUTE)
         or (cre.startTime <= DATE_SUB(?, INTERVAL 30 MINUTE) and cre.endTime >= DATE_ADD(?, INTERVAL 30 MINUTE)
         ) //end or
         ) // end and
*/
    }

     @Test
    public void testListByConditionsConRApplyRecord(){
            Integer auditState = 0;
            String depId = "公关部";
            Integer startIndex = 0;
            Integer deleted = null;
         List<ConRApplyRecord> conRApplyRecords = conferenceRecordService.listByConditions(auditState, depId,startIndex,deleted);
         for (ConRApplyRecord c:
              conRApplyRecords) {
             System.out.println(c);
         }
     }

     @Test
    public void testEmail() throws SystemException {
        Map<String, Object> content = new HashMap<>();
        content.put("roomFloor","11");
        content.put("roomNo","302");
        content.put("startTime","2024-07-22 15:02:00");
         content.put("endTime","2024-07-22 15:02:00");
         content.put("result","申请通过");
         content.put("theme","asdasda");
         content.put("applyTime","2024-07-22 15:02:00");
       //boolean b =  emailService.sendStartMail("dandan@qq.com","测试邮件发送",content);


       emailService.sendAuditMail("dandan@qq.com","测试审核结果通知",content);

     }

     @Test
    public void testLombok(){
         Employee employee = new Employee();
         employee.setRole("admin").setEName("ceshi");
         System.out.println(employee);
     }

    @Test
    public void testJWT(){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + 3600 * 1000);
        Admin admin = new Admin();
        admin.setId(100);
        admin.setUsername("zouyongfa");
        admin.setPassword("aaaaa");
        admin.setRole("admin");

        String s = JSON.toJSONString(admin);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(s)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, "f4e2e52034348f86b67cde581c0f9eb5")
                .compact();

        System.out.println(JSON.parseObject(jwtUtils.getClaimByToken(jwt).getSubject(),Admin.class).toString());

    }




}
