package com.yifan.wx.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class Test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //第一种方法：forName
        try {
            Class<?> class1 = Class.forName("com.yifan.wx.reflect.Person");

            System.out.println(class1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //第二张方法：class
        Class<?> class2 = Person.class;
        System.out.println(class2);
        //第三种方法：getClass
        Person person = new Person();
        Class<?> class3 = person.getClass();
        System.out.println(class3);
        // 获取方法
        Method[] methods = class2.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        // 获取接口
        Class<?>[] interS = class2.getInterfaces();
        for (Class<?> inter : interS) {
            System.out.println(inter);
        }

        //获取父类
        Class<?> superclass = class2.getSuperclass();

        System.out.println(superclass);

        // 获取构造函数
        Constructor<?>[] constructors = class2.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        //取得本类的全部属性
        Field[] field = class2.getDeclaredFields();
        for (Field field2 : field) {
            System.out.println(field2);
        }

        //创建实例化：相当于 new 了一个对象
        Object object = class2.newInstance();
        //向下转型
        Person person2 = (Person) object;
        System.out.println(person2);

        //获得所有的字段属性：包括public、private和proteced，但是不包括父类的申明字段
        Field[] declaredFields = class2.getDeclaredFields();
        // 某个类的所有的公共（public）的字段，包括父类。
        Field[] fields = class2.getFields();

        for (Field f : declaredFields) {
            System.out.println("de--  " + f);
        }

        for (Field f : fields) {
            System.out.println("field--  " + f);
        }
    }
}
