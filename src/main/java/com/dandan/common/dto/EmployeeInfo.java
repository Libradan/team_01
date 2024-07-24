package com.dandan.common.dto;

import com.dandan.entity.Department;
import com.dandan.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dandan
 * @Date 2024/7/23 15:56
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
    private Employee employee;
    private Department department;
}
