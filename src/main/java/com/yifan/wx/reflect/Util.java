package com.yifan.wx.reflect;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class Util {

    public static String name = "json";

    /**
     * 没有返回值，没有参数
     */
    public static void getTips() {
        System.out.println("执行了---------1111");
    }

    /**
     * 有返回值，没有参数
     */
    public static String getTip() {
        System.out.println("执行了---------2222");
        return "tip2";
    }

    /**
     * 没有返回值，有参数
     */
    public static void getTip(String name) {
        System.out.println("执行了---------3333 参数： " + name);
    }

    /**
     * 有返回值，有参数
     */
    public static String getTip(int id) {
        System.out.println("执行了---------4444 参数： " + id);
        if (id == 0) {
            return "tip1 444 --1 ";
        } else {
            return "tip1 444 --2";
        }
    }
}
