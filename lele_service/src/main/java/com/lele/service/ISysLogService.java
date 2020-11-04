package com.lele.service;

import com.lele.pojo.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
