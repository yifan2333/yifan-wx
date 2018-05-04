package com.yifan.wx.agency;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class MyInvocationHandler implements InvocationHandler {

    private static List<String> list;

    private Object object;

    MyInvocationHandler(Object object) {
        this.object = object;
    }

    {
        System.out.println("init Method list start...");
        list = new ArrayList<>();
        try {
            for (Method method : this.getClass().getClassLoader().loadClass(("com.yifan.wx.agency.UserServiceImpl")).getMethods()) {
                if (method.isAnnotationPresent(LoggerAnnotation .class)) {
                    LoggerAnnotation LoggerAnnotation = method.getAnnotation(LoggerAnnotation.class);
                    if (LoggerAnnotation.isPrint()) {
                        list.add(method.getName());
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("init Method list end, result is" + JSONObject.toJSONString(list));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(list.contains(method.getName())){
            System.out.println("++++++before " + method.getName() + "++++++");
            Object result = method.invoke(this.object, args);
            System.out.println("++++++after " + method.getName() + "++++++");
            return result;
        }else{
            return method.invoke(proxy, args);
        }
    }
}
