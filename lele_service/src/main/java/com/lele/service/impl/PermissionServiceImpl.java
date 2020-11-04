package com.lele.service.impl;

import com.lele.dao.PermissionDao;
import com.lele.pojo.Permission;
import com.lele.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {

        return permissionDao.findAll();

    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
