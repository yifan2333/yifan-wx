package com.yifan.wx.reflect;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class Person implements InterFace {

    private String id ;

    private String name ;

    public String age ;

    public Person() {
    }

    public Person(String id) {
        this.id = id;
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 静态方法
     */
    public static void update(){

    }


    @Override
    public void read() {

    }
}
