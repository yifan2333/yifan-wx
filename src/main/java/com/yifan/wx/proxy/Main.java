package com.yifan.wx.proxy;

/**
 * @author yifan
 * @since 2018年05月08日
 */
public class Main {
    public static void main(String[] args) {
        Hello hello = new Hello();
        HelloProxy helloProxy = new HelloProxy(hello);
        helloProxy.getProxyInstance();
        hello.setId(1);

    }
}
