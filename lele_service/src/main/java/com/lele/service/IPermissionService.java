package com.lele.service;

import com.lele.pojo.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);
}

