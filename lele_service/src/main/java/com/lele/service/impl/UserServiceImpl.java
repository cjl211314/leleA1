package com.lele.service.impl;

import com.lele.dao.RoleDao;
import com.lele.dao.SysLogDao;
import com.lele.dao.UserDao;
import com.lele.pojo.Role;
import com.lele.pojo.SysLog;
import com.lele.pojo.Userinfo;
import com.lele.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userinfo userinfo = userDao.findByUsername(username);
        User user = new User(userinfo.getUsername(),userinfo.getPassword(),userinfo.getStatus()==0?false:true,true,true,true,getAuthority(userinfo.getRoles()));

        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roleList) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;

    }

    //查询全部  用户
    @Override
    public List<Userinfo> findAll() {
        return userDao.findAll();
    }
//添加用户
    @Override
    public void save(Userinfo userinfo) {
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
        userDao.save(userinfo);
    }

    @Override
    public Userinfo findById(String id) {
        return userDao.findById(id);

    }

    @Override
    public List<Role> findOtherRolers(String userId) {
        return userDao.findOtherRolers(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roles) {
        for (String roleId : roles) {
            userDao.addRoleToUser(userId,roleId);
        }

    }


}
