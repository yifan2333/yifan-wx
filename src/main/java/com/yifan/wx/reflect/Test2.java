package com.yifan.wx.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        //创建类
        Class<?> class1 = Class.forName("com.yifan.wx.reflect.Person");
        //创建实例化：相当于 new 了一个对象
        Object object = class1.newInstance();

        //向下转型
        Person person = (Person) object;

        person.setId("100");
        person.setName("jack");
        System.out.println("id: " + person.getId() + " name: " + person.getName());

        System.out.println();

        //创建实例
        person = (Person) class1.newInstance();

        //获得id 属性
        Field idField = class1.getDeclaredField("id");

        //打破封装  实际上setAccessible是启用和禁用访问安全检查的开关, 并不是为true就能访问为false就不能访问
        //由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
        idField.setAccessible(true);

        //给id 属性赋值
        idField.set(person, "100");

        //打印 person 的属性值
        System.out.println(idField.get(person));

        //获取 setName() 方法
        Method setName = class1.getDeclaredMethod("setName", String.class);
        //打破封装
        setName.setAccessible(true);

        //调用setName 方法。
        setName.invoke(person, "jack");

        //获取name 字段
        Field nameField = class1.getDeclaredField("name");
        //打破封装
        nameField.setAccessible(true);

        //打印 person 的 id 属性值
        String id_ = (String) idField.get(person);
        System.out.println("id: " + id_);

        //打印 person 的 name 属性值
        String name_ = (String) nameField.get(person);
        System.out.println("name: " + name_);

        //获取 getName 方法
        Method getName = class1.getDeclaredMethod("getName");
        //打破封装
        getName.setAccessible(true);

        //执行getName方法，并且接收返回值
        String name_2 = (String) getName.invoke(person);
        System.out.println("name2: " + name_2);
    }
}
