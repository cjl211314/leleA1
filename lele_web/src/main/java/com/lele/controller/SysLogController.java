package com.lele.controller;

import com.lele.pojo.SysLog;
import com.lele.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private ISysLogService syslogService;

    @RequestMapping("/findAll.do")
    private ModelAndView findAll(){
        List<SysLog>  list = syslogService.findAll();
        ModelAndView  mv = new ModelAndView();
        mv.addObject("sysLogs",list);
        mv.setViewName("syslog-list");
        return mv;
    }
}
