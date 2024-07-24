package com.dandan.service.impl;

import com.dandan.entity.ConferenceRecord;
import com.dandan.entity.group.ConRApplyRecord;
import com.dandan.mapper.ConferenceRecordMapper;
import com.dandan.service.ConferenceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dandan
 * @since 2024-07-18
 */
@Service
public class ConferenceRecordServiceImpl extends ServiceImpl<ConferenceRecordMapper, ConferenceRecord> implements ConferenceRecordService {

    @Autowired
    ConferenceRecordMapper conferenceRecordMapper;

    @Override
    public List<ConRApplyRecord> listByConditions( Integer auditState,
                                                  String depName,Integer currentPage,Integer deleted){

        //index从0开始
        Integer startIndex = (currentPage-1)*7; //一页7条数据

        return conferenceRecordMapper.listByConditions(auditState,depName,startIndex,deleted);
    }

    @Override
    public Integer getTotalByConditions(Integer auditState, String depName,Integer deleted) {
        return conferenceRecordMapper.getTotalByConditions(auditState,depName,deleted);
    }

}
