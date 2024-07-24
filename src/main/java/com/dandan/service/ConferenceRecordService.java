package com.dandan.service;

import com.dandan.entity.ConferenceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dandan.entity.group.ConRApplyRecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dandan
 * @since 2024-07-18
 */
public interface ConferenceRecordService extends IService<ConferenceRecord> {

    List<ConRApplyRecord> listByConditions(Integer auditState,
                                           String depName,Integer currentPage,Integer deleted);

    Integer getTotalByConditions( Integer auditState,
                                  String depName,Integer deleted);
}
