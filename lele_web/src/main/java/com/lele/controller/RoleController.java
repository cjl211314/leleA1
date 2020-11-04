package com.lele.controller;

import com.lele.pojo.Permission;
import com.lele.pojo.Role;
import com.lele.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdPermission")
    public ModelAndView findRoleByIdPermission(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleId",id);
        List<Permission> list = roleService.findByRoleIdOtherPermission(id);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;
    }




    @RequestMapping("/save")
    private String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        List<Role> roleList = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }
}
