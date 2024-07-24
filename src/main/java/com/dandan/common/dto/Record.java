package com.dandan.common.dto;

import com.dandan.entity.Apply;
import com.dandan.entity.ConferenceRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 申请会议室时前后端交互的数据载体类
 * @Author dandan
 * @Date 2024/7 /23 0:46
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private Apply apply;
    private ConferenceRecord conferenceRecord;

}
