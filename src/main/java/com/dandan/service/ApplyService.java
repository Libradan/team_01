package com.dandan.service;

import com.dandan.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dandan
 * @since 2024-07-18
 */
public interface ApplyService extends IService<Apply> {

    List<Integer> searchTimeConflict(Integer roomId, LocalDateTime startTime, LocalDateTime endTime);
}
