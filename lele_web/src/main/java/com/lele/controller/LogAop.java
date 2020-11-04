package com.lele.controller;

import com.lele.pojo.SysLog;
import com.lele.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component  //注入到spring容器
@Aspect  //切入点
public class LogAop {
    private Date visitTime; //开始时间
    private Class claszz; //访问类
    private Method method;// 访问方式

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    //前置通知
    @Before("execution(* com.lele.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        claszz = jp.getTarget().getClass(); //访问的具体类
        //获取执行的方法名称      //获取访问的方法名称
        String methodName = jp.getSignature().getName();

        //获取访问的方法参数
        Object[] objects = jp.getArgs();
        if (objects == null || objects.length == 0) {
            method = claszz.getMethod(methodName); //只能获取到无参的方法
        } else {
            Class[] classss = new Class[objects.length];
            for (int i = 0; i < objects.length; i++) {
                classss[i] = objects[i].getClass();
            }
            //封装参数
            method = claszz.getMethod(methodName, classss);
        }

    }

    //后置通知
    @After("execution(* com.lele.controller.*.*(..))")
    public void doAfter() {
        Long time = new Date().getTime() - visitTime.getTime();  //获取访问的时长
        //通过java反射的方式获取访问的url
        String url = "";
        if (claszz != null && method != null && claszz != LogAop.class) {
            //获取类上的RequestMapping注解 获取注解里的内容
            RequestMapping classAnnotion = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
            if (classAnnotion != null) {
                String[] value = classAnnotion.value(); //获取到类上RequestMapping的value值
                //获取到方法上的RequestMapping注解
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                //取出里面的value值
                String[] value1 = annotation.value();
                url = value[0] + value1[0];

                //获取请求的ip地址
                String ip = request.getRemoteAddr();

                //获取当前操作的用户
                SecurityContext context = SecurityContextHolder.getContext();
                //获取当前操作用户对象
                User principal = (User) context.getAuthentication().getPrincipal();
                //获取用户名
                String username = principal.getUsername();

                SysLog sysLog = new SysLog();
                sysLog.setIp(ip);//地址
                sysLog.setExecutionTime(time);//执行时长
                sysLog.setMethod("[类名] " + claszz.getName() + "[方法名] " + method.getName());//访问的方法
                sysLog.setUrl(url); //请求的url
                sysLog.setUsername(username); //访问的用户
                sysLog.setVisitTime(visitTime); //访问的时间

                sysLogService.save(sysLog);
            }

        }


    }
}
