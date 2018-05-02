package com.yifan.wx.annotation;

import java.util.List;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class TestAnnotation {
    @SuppressWarnings({ "unchecked"})
    public static void main(String[] args) {
        Parent c = new Child();
        //获取泛型中类里面的注解
        List<SortableField> list = c.init();
        //输出结果
        for(SortableField l : list){
            System.out.println("字段名称："+l.getName()+"\t字段类型："+l.getType()+
                    "\t注解名称："+l.getMeta().name()+"\t注解描述："+l.getMeta().description());
        }
    }
}
