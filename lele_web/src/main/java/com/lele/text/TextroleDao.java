package com.lele.text;

import com.lele.dao.RoleDao;
import com.lele.dao.UserDao;
import com.lele.pojo.Role;
import com.lele.pojo.Userinfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TextroleDao {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
/*        RoleDao roleDao = context.getBean(RoleDao.class);
        List<Role> list = roleDao.findAll();
        for (Role role : list) {
            System.out.println(role.getRoleName());
        }*/

        UserDao bean = context.getBean(UserDao.class);
        Userinfo byId = bean.findById("1");
        System.out.println(byId.getUsername());
        List<Role> roles = byId.getRoles();
        for (Role role : roles) {
            System.out.println(role.getRoleName());
        }
    }
}
