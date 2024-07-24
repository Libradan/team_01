package com.dandan.mapper;

import com.dandan.entity.ConferenceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandan.entity.group.ConRApplyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dandan
 * @since 2024-07-18
 */

@Repository
public interface ConferenceRecordMapper extends BaseMapper<ConferenceRecord> {

     List<ConRApplyRecord> listByConditions(@Param("auditState") Integer auditState,
                                            @Param("depName") String depName,
                                            @Param("startIndex") Integer startIndex,
                                            @Param("deleted") Integer deleted);

     Integer getTotalByConditions(@Param("auditState") Integer auditState,
                                  @Param("depName") String depName,@Param("deleted") Integer deleted);
}
