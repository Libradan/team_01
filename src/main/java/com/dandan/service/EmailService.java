package com.dandan.service;

import com.dandan.common.exception.SystemException;

import java.util.Map;

/**
 * @Author dandan
 * @Date 2024/7 /22 14:24
 * @Description
 */
public interface EmailService {

    boolean sendStartMail(String to, String subject, Map<String,Object> content) throws SystemException;

    boolean sendAuditMail(String to, String subject, Map<String,Object> content) throws SystemException;
}
