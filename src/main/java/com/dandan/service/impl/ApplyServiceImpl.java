package com.dandan.service.impl;

import com.dandan.entity.Apply;
import com.dandan.mapper.ApplyMapper;
import com.dandan.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {
    @Autowired
    ApplyMapper applyMapper;


    @Override
    public List<Integer> searchTimeConflict(Integer roomId, LocalDateTime startTime, LocalDateTime endTime) {
        return applyMapper.searchTimeConflict(roomId,startTime,endTime);
    }
}
