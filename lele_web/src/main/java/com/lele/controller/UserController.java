package com.lele.controller;

import com.lele.pojo.Role;
import com.lele.pojo.Userinfo;
import com.lele.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //用户关联角色操作-添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roles){
        userService.addRoleToUser(userId,roles);
        return "redirect:findAll.do";
    }

//用户关联角色操作
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId){
        Userinfo userinfo = userService.findById(userId);

        //查询出该用户没有的角色信息
        List<Role> roleList = userService.findOtherRolers(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userinfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }


//查询用户所有详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Userinfo userinfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userinfo);
        mv.setViewName("user_show1");
        return mv;
    }


//添加用户
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='aaa' or hasRole('ROLE_ADMIN')")
    public String save(Userinfo userinfo){
        userService.save(userinfo);
        return "redirect:findAll.do";
    }


//查询全部用户

    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(){
        List<Userinfo> userinfoList = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userinfoList);
        mv.setViewName("user-list");
        return mv;
    }


}
