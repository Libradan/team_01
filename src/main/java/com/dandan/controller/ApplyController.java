package com.dandan.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dandan.common.dto.Record;
import com.dandan.common.lang.Result;
import com.dandan.entity.Apply;
import com.dandan.entity.ConferenceRecord;
import com.dandan.entity.ConferenceRoom;
import com.dandan.service.ApplyService;
import com.dandan.service.ConferenceRecordService;
import com.dandan.service.ConferenceRoomService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  申请会议室时所需要的api
 * </p>
 *
 * @author dandan
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {


    @Autowired
    ApplyService applyService;

    @Autowired
    ConferenceRecordService conferenceRecordService;

    @Autowired
    ConferenceRoomService conferenceRoomService;


    /**
     * {
          {
            depId
            roomId
           }
           {
            statrTime
            endTime
            theme
            digest
            personCount

           }
       }
     *
     *
     * 添加一个会议室申请申请
     * @return
     */
    @PostMapping("/add")
    @RequiresRoles(value = "user")
    public Result add(@RequestBody Record record){

        Apply apply = record.getApply();
        ConferenceRecord conferenceRecord = record.getConferenceRecord();
        applyService.save(apply);

        //要设置外键
        conferenceRecord.setApplyId(apply.getApplyId());
        conferenceRecordService.save(conferenceRecord);

        return Result.succ("");

    }

    /**
     * 管理员紧急申请
     * @param record
     * @return
     */
    @PostMapping("/addbyadmin")
    @RequiresRoles(value = "admin")
    public Result addByAdmin(@RequestBody Record record){

        System.out.println(record);

        Apply apply = record.getApply();
        ConferenceRecord conferenceRecord = record.getConferenceRecord();
        applyService.save(apply);

        System.out.println(apply);

        //直接通过  因为时间冲突在前端判断过了  save会将id回显给apply
        applyService.update(new UpdateWrapper<Apply>().eq("apply_id",apply.getApplyId())
        .set("audit_state",1));

        //要设置外键
        conferenceRecord.setApplyId(apply.getApplyId());
        conferenceRecordService.save(conferenceRecord);

        return Result.succ("");

    }


    /**
     * 查出来如果是List不为空 则证明有冲突
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/searchtimeconflict/{roomId}/{startTime}/{endTime}")
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    public Result searchtimeconflict(@PathVariable("roomId") Integer roomId,
                                     @PathVariable("startTime")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                     @PathVariable("endTime")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){


        List<Integer> listApplyId = applyService.searchTimeConflict(roomId,startTime,endTime);

        if(listApplyId.size()>0){
            return Result.succ("0");
        }
        return Result.succ("1");
    }


    /**
     * 根据id查一个会议室  供页面申请会议室时 点击申请 就是到后台拿到这个会议室的所有数据
     */
    @GetMapping("/getconfroom/{id}")
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    public Result getById(@PathVariable("id") Integer id){
        ConferenceRoom byId = conferenceRoomService.getById(id);
        return Result.succ(byId);
    }


}
