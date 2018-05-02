package com.yifan.wx.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class Test3 {

    public static void main(String[] args) {
        try {
            //创建类
            Class<?> class1 = Class.forName("com.yifan.wx.reflect.Util");

            //获取 nameField 属性
            Field nameField = class1.getDeclaredField("name");
            //获取 nameField 的值
            String name_ = (String) nameField.get(nameField);
            //输出值
            System.out.println(name_);


            //没有返回值，没有参数
            Method getTipMethod1 = class1.getDeclaredMethod("getTips");
            getTipMethod1.invoke(null);

            //有返回值，没有参数
            Method getTipMethod2 = class1.getDeclaredMethod("getTip");
            String result_2 = (String) getTipMethod2.invoke(null);
            System.out.println("返回值： " + result_2);

            //没有返回值，有参数
            Method getTipMethod3 = class1.getDeclaredMethod("getTip", String.class);
            String result_3 = (String) getTipMethod3.invoke(null, "第三个方法");
            System.out.println("返回值： " + result_3);

            //有返回值，有参数
            Method getTipMethod4 = class1.getDeclaredMethod("getTip", int.class);
            String result_4 = (String) getTipMethod4.invoke(null, 1);
            System.out.println("返回值： " + result_4);

        } catch (InvocationTargetException | IllegalArgumentException
                | IllegalAccessException | NoSuchMethodException
                | SecurityException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
