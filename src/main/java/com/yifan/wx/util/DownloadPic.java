package com.yifan.wx.util;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class DownloadPic {
    public static void main(String[] args) throws Exception {
        java.awt.Robot m1 = new java.awt.Robot();
        for(int i = 0 ; i <=10000 ; i++){
            m1.mouseMove((int)(Math.random()*1000), (int)(Math.random()*1000));
        }
        System.out.println("哈哈，放过你，没给你写成死循环");
    }
}
