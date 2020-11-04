package com.lele.controller;


import com.github.pagehelper.PageInfo;
import com.lele.pojo.Orders;
import com.lele.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;
//查询订单列表
    @RequestMapping("/findAll")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{
        ModelAndView mo = new ModelAndView();
        List<Orders> list = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mo.addObject("pageInfo",pageInfo);
        mo.setViewName("orders-list");
        return mo;
    }
//查询订单列表
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String orderId)throws Exception{
        Orders orders = orderService.findById(orderId);
        ModelAndView mo = new ModelAndView();
        mo.addObject("orders",orders);
        mo.setViewName("orders-show");
        return mo;
    }


}
