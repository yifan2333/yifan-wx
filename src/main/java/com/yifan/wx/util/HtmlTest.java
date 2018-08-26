package com.yifan.wx.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年07月12日 17:21
 */
public class HtmlTest {

    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public static void main(String[] args) {
        HtmlTest a = new HtmlTest();
        print(a.getA());
    }

    public static void print (int i) {
        System.out.println(i);
    }

}
