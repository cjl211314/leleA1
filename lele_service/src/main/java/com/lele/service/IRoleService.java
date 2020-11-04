package com.lele.service;

import com.lele.pojo.Permission;
import com.lele.pojo.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findByRoleIdOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
