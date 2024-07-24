package com.dandan.service.impl;

import com.dandan.entity.Employee;
import com.dandan.mapper.EmployeeMapper;
import com.dandan.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dandan
 * @since 2024-07-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
