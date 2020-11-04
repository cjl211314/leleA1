package com.lele.service;

import com.lele.pojo.Role;
import com.lele.pojo.SysLog;
import com.lele.pojo.Userinfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<Userinfo> findAll();
    void save(Userinfo userinfo);

    Userinfo findById(String id);

    List<Role> findOtherRolers(String userId);

    void addRoleToUser(String userId, String[] roles);


}
