package com.yifan.wx.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yifan
 * @since 2018年05月08日
 */
public class HelloProxy {
    private Object target;

    public HelloProxy(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始事务2");
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务2");
                    return returnValue;
                }
        );
    }
}
